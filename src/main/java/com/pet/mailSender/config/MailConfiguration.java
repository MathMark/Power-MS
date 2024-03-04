package com.pet.mailSender.config;

import com.pet.mailSender.config.properties.MailProperties;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Getter
@Component
public class MailConfiguration {
    Properties properties;
    
    public MailConfiguration(MailProperties mailProperties) {
        properties = new Properties();
        properties.put("mail.smtp.auth", mailProperties.getAuth());
        properties.put("mail.smtp.starttls.enable", mailProperties.getStartTLS());
        properties.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        properties.put("mail.smtp.host", mailProperties.getHost());
        properties.put("mail.smtp.port", mailProperties.getPort());
        properties.put("mail.smtp.sasl.mechanisms.oauth2.oauthToken", "");
    }
}
