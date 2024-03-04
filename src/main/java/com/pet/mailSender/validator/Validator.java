package com.pet.mailSender.validator;

import com.pet.mailSender.config.MailConfiguration;

import com.sun.mail.smtp.SMTPTransport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@RequiredArgsConstructor
@Slf4j
public class Validator {
    private final OAuth2AuthorizedClientService clientService;

    private final MailConfiguration mailConfiguration;

    public void validateCredentials(OAuth2AuthenticationToken token) throws MessagingException {
        OAuth2AuthorizedClient client =
    clientService.loadAuthorizedClient(
            token.getAuthorizedClientRegistrationId(),
            token.getName());
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.imap.ssl.enable", "true"); // required for Gmail
        props.put("mail.imap.sasl.enable", "true");
        props.put("mail.imap.sasl.mechanisms", "XOAUTH2");
        props.put("mail.imap.auth.login.disable", "true");
        props.put("mail.imap.auth.plain.disable", "true");
        Session session = Session.getInstance(props);
        Transport transport = session.getTransport();
        transport.connect("imap.gmail.com", client.getPrincipalName(), client.getAccessToken().getTokenValue());
        
        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("vadimmartsun@gmail.com"));
                        message.setRecipients(
                                Message.RecipientType.TO,
                                InternetAddress.parse("gbsfoai@gmail.com")
                        );
                        message.setSubject("fdvd");
                        message.setContent("Hello!", "text/html");
        
        transport.sendMessage(message, message.getAllRecipients());
        
        
        

            
                        
    }
}
