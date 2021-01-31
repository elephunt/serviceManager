package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import com.margolin.project.servicemanager.app.main.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    final
    ServiceRepository apiRepository;

    public ApiServiceImpl(ServiceRepository apiRepository) {
        this.apiRepository = apiRepository;
    }


    @Override
    public List<ApiModelDto> getApiByName(String name){
        List<ServiceModelDto> serviceModelDtos = this.apiRepository.findByApiName(name);
        return  serviceModelDtos.stream().map(ServiceModelDto::getApis)
                .flatMap(List::stream).collect(Collectors.toList()) ;}


}
