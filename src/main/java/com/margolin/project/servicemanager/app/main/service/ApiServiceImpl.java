package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.exceptions.GeneralException;
import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.repository.ApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
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
        if(CollectionUtils.isEmpty(apis)){
            log.info("List of apis is empty");
            return Collections.emptyList();
        }
        apis.stream().filter(ap -> !ObjectUtils.isEmpty(ap.getName()))
                .forEach(ap -> ap.setName(ap.getName().toLowerCase()));
        return this.apiRepository.saveAll(apis);
    }
}
