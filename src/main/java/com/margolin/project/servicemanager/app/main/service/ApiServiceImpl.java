package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {



    private final IServiceManager serviceManager;
    final
    MongoTemplate mongoTemplate;

    public ApiServiceImpl(IServiceManager serviceManager, MongoTemplate mongoTemplate) {
        this.serviceManager = serviceManager;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<ApiModelDto> getApiByName(String name){
        List<ServiceModelDto> serviceModelDtos = this.serviceManager.findServiceThatHaveApi(name);
        return  serviceModelDtos.stream().map(ServiceModelDto::getApis).flatMap(List::stream).collect(Collectors.toList());
    }
}
