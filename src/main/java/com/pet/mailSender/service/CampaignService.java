package com.pet.mailSender.service;

import com.pet.mailSender.model.Campaign;
import com.pet.mailSender.model.viewModels.CampaignView;
import com.pet.mailSender.repository.CampaignRepository;
import com.pet.mailSender.service.emailSender.EmailSender;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignDao;

//    private final CampaignMapper campaignMapper;

    @Autowired
    private ObjectFactory<EmailSender> objectFactory;
    
    private Map<Integer, Thread> threadMap = new HashMap<>();

    public List<Campaign> getAll(){
        return campaignDao.findAll();
    }

    public Campaign getCampaignById(int id){
        return campaignDao.findById(id).get();
    }

    public void save(Campaign campaign){
        campaignDao.save(campaign);
    }

    public void deleteCampaign(Campaign campaign){
        campaignDao.delete(campaign);
    }

    public void saveAsCampaign(CampaignView campaignView){
        //Campaign campaign = campaignMapper.getCampaign(campaignView);
        //save(campaign);
    }

    public void runCampaignParallel(int campaignId){
        Campaign campaign = campaignDao.findById(campaignId).get();
        if(campaign != null){
            EmailSender e = objectFactory.getObject();
            e.setCampaign(campaign);
            Thread thread = new Thread(e);
            threadMap.put(e.getCampaign().getId(), thread);
            thread.start();
        }
    }

    public void stopCampaign(int campaignId){
        Campaign campaign = campaignDao.findById(campaignId).get();
        if(campaign != null){
            Thread thread = threadMap.remove(campaignId);
            if(thread != null && !thread.isInterrupted()){
                thread.interrupt();
            }
        }
    }

}
