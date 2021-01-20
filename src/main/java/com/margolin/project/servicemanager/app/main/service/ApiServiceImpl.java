package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    final
    ApiRepository apiRepository;

    public ApiServiceImpl(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public List<ApiModelDto> getApiByName(String name){
        return  this.apiRepository.findByNameLike(name);
    }

    @Override
    public List<ApiModelDto> saveApis(List<ApiModelDto> apis) {
        apis.stream().filter(ap -> !ObjectUtils.isEmpty(ap.getName()))
                .forEach(ap -> ap.setName(ap.getName().toLowerCase()));
        return this.apiRepository.saveAll(apis);
    }
}
