package com.pet.mailSender.service.emailSender;

import com.pet.mailSender.config.properties.MailProperties;
import com.pet.mailSender.model.Account;
import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.enums.CampaignStatus;
import com.pet.mailSender.model.enums.EmailStatus;
import com.pet.mailSender.model.Person;
import com.pet.mailSender.service.utilities.ProgressCalculator;
import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class EmailSender implements Runnable {

    private Properties properties;
    //private Dao<Campaign> campaignDao;
    private ProgressCalculator progressCalculator;

    public EmailSender(ProgressCalculator progressCalculator, MailProperties mailProperties) {
        //this.campaignDao = campaignDao;
        this.progressCalculator = progressCalculator;
        initializeProperties(mailProperties);
    }

    private void initializeProperties(MailProperties mailProperties) {
        properties = new Properties();
        properties.put("mail.smtp.host", mailProperties.getHost());
        properties.put("mail.smtp.port", mailProperties.getPort());
        properties.put("mail.smtp.auth", mailProperties.getAuth());
        properties.put("mail.smtp.starttls.enable", mailProperties.getStartTLS());
    }

    @Getter
    @Setter
    private Campaign campaign;

    private void sendEmails() {

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(campaign.getAccount().getEmail(), campaign.getAccount().getPassword());
                    }
                });

        int sentCount = (int)campaign.getPeople().stream().filter(p -> p.getEmailStatus().equals(EmailStatus.SENT)).count();
        int rejectedCount = (int)campaign.getPeople().stream().filter(p -> p.getEmailStatus().equals(EmailStatus.REJECTED)).count();
        boolean isStopped = false;

        Person[] people = new Person[campaign.getPeople().size()];
        campaign.getPeople().toArray(people);

        campaign.getEmailStatistics().setCampaignStatus(CampaignStatus.RUNNING);
        try{
            for (int i = 0; i < people.length; i++) {
                if(Thread.currentThread().isInterrupted()){
                    campaign.getEmailStatistics().setCampaignStatus(CampaignStatus.STOPPED);
                    System.out.println(campaign.getTitle() + " " + campaign.getEmailStatistics().getCampaignStatus());
                    //campaignDao.update(campaign);
                    isStopped = true;
                    break;
                }
                try {
                    EmailStatus emailStatus = people[i].getEmailStatus();

                    if(emailStatus == null || (!emailStatus.equals(EmailStatus.SENT))){
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(campaign.getAccount().getEmail()));
                        message.setRecipients(
                                Message.RecipientType.TO,
                                InternetAddress.parse(people[i].getEmail())
                        );
                        message.setSubject(campaign.getTemplate().getSubject());
                        message.setContent(campaign.getTemplate().getBody(), "text/html");

                        Transport.send(message);

                        System.out.println("Sending message");
                        people[i].setEmailStatus(EmailStatus.SENT);
                        sentCount ++;
                        campaign.getEmailStatistics().setSentEmailsCount(sentCount);

                        campaign.getEmailStatistics().setProgress(progressCalculator.getProgress(campaign.getPeople().size(),
                                campaign.getEmailStatistics().getSentEmailsCount()));

                        //campaignDao.update(campaign);
                        Thread.sleep(campaign.getDelay());
                    }

                } catch (MessagingException e) {
                    e.printStackTrace();
                    rejectedCount ++;
                    people[i].setEmailStatus(EmailStatus.REJECTED);
                    campaign.getEmailStatistics().setRejectedEmailsCount(rejectedCount);
                }

            }
        }catch (InterruptedException e){
            campaign.getEmailStatistics().setCampaignStatus(CampaignStatus.STOPPED);
            System.out.println(campaign.getTitle() + " " + campaign.getEmailStatistics().getCampaignStatus());
            //campaignDao.update(campaign);
            isStopped = true;
        }

        if(!isStopped){
            campaign.getEmailStatistics().setCampaignStatus(CampaignStatus.FINISHED);
        }
    }

    @Override
    public void run() {
        sendEmails();
    }

    public boolean validateCredentials(Account account){
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(account.getEmail(), account.getPassword());
                    }
                });
        Transport transport;

        try {
            transport = session.getTransport("smtp");
            transport.connect();
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
