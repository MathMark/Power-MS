//package com.pet.mailSender.service.mappers.campaignMapper;
//
//import com.pet.mailSender.model.*;
//import com.pet.mailSender.model.enums.CampaignStatus;
//import com.pet.mailSender.model.viewModels.CampaignView;
//import com.pet.mailSender.service.parsers.csvParser.Parser;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//public class CampaignMapperImpl implements CampaignMapper {
//
//    private Parser<Person> personParser;
//
//    private Dao<Template> templateDao;
//
//    private Dao<Account> accountDao;
//
//    public CampaignMapperImpl(Dao<Template> templateDao, Dao<Account> accountDao, Parser<Person> personParser) {
//        this.templateDao = templateDao;
//        this.accountDao = accountDao;
//        this.personParser = personParser;
//    }
//
//    @Override
//    public Campaign getCampaign(Account account, Template template) {
//        Campaign campaign = new Campaign();
//        campaign.setTitle(campaignView.getTitle());
//        campaign.setDelay(Long.parseLong(campaignView.getDelay())*1000);
//
//        campaign.setAccount(accountDao.getById(campaignView.getAccount().getId()));
//        campaign.setTemplate(templateDao.getById(campaignView.getTemplate().getId()));
//
//        EmailStatistics emailStatistics = new EmailStatistics();
//        emailStatistics.setCampaignStatus(CampaignStatus.NEW);
//        campaign.setEmailStatistics(emailStatistics);
//
//        Set<Person> peopleList = getPeopleList(campaignView);
//        if(peopleList != null){
//            campaign.addPeople(peopleList);
//        }
//
//        return campaign;
//    }
//
//    @Override
//    public CampaignView getCampaignView(Campaign campaign) {
//        //TODO
//        return null;
//    }
//
//    private Set<Person> getPeopleList(CampaignView campaignView){
//        try {
//            return new HashSet<>(personParser.getJavaObjects
//                    (campaignView.getPeopleList().getInputStream(), Person.class)
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
