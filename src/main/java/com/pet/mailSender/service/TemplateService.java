package com.pet.mailSender.service;

import com.pet.mailSender.model.Template;
import com.pet.mailSender.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateDao;
    
    public List<Template> getAll(){
        return templateDao.findAll();
    }

    public void save(Template template){
        templateDao.save(template);
    }
}
