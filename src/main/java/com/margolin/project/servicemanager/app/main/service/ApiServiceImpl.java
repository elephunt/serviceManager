package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApiServiceImpl implements ApiService {

    @Autowired
    ApiRepository apiRepository;

    @Override
    public List<ApiModelDto> getApiByName(String name){
        return  this.apiRepository.findByNameLike(name);
    }
}
