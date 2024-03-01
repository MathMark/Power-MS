package com.pet.mailSender.config;

import com.pet.mailSender.config.properties.MailProperties;
import com.pet.mailSender.model.Person;
import com.pet.mailSender.service.emailSender.EmailSender;
import com.pet.mailSender.service.parsers.csvParser.CsvParser;
import com.pet.mailSender.service.parsers.csvParser.Parser;
import com.pet.mailSender.service.utilities.ProgressCalculator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class ApplicationConfiguration {


    @Bean
    public Parser<Person> personParser() {
        return new CsvParser<Person>();
    }

    @Bean
    public ProgressCalculator progressCalculator() {
        return new ProgressCalculator();
    }

    @Bean
    @Scope("prototype")
    public EmailSender emailSender(ProgressCalculator progressCalculator,
                                   MailProperties mailProperties) {
        return new EmailSender(progressCalculator, mailProperties);
    }

    @Bean
    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
        return (factory) -> factory.setRegisterDefaultServlet(true);
    }
}
