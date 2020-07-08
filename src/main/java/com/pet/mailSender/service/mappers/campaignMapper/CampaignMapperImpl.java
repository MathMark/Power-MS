package com.pet.mailSender.service.mappers.campaignMapper;

import com.pet.mailSender.model.*;
import com.pet.mailSender.model.enums.CampaignStatus;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.service.parsers.csvParser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class CampaignMapperImpl implements CampaignMapper {

    @Autowired
    private Parser<Person> personParser;

    @Override
    public Campaign getCampaign(CampaignView campaignView) {
        Campaign campaign = new Campaign();
        campaign.setTitle(campaignView.getTitle());
        campaign.setDelay(Long.parseLong(campaignView.getDelay())*1000);

        campaign.setAccount(campaignView.getAccount());

        campaignView.getTemplate().setTitle(campaignView.getTitle() + "-template");
        campaign.setTemplate(campaignView.getTemplate());

        EmailStatistics emailStatistics = new EmailStatistics();
        emailStatistics.setCampaignStatus(CampaignStatus.NEW);
        campaign.setEmailStatistics(emailStatistics);

        Set<Person> peopleList = getPeopleList(campaignView);
        if(peopleList != null){
            campaign.addPeople(peopleList);
        }

        return campaign;
    }

    @Override
    public CampaignView getCampaignView(Campaign campaign) {
        //TODO
        return null;
    }

    private Set<Person> getPeopleList(CampaignView campaignView){
        try {
            return new HashSet<>(personParser.getJavaObjects
                    (campaignView.getPeopleList().getInputStream(), Person.class)
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
