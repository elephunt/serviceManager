package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;

import java.util.List;

public interface IServiceManager {
    ServiceModelDto getServiceModel(String id);

    ServiceModelDto saveServiceModel(ServiceModelDto serviceModelDto);

    ServiceModelDto updateModel(ServiceModelDto updatedServiceModel);

    List<ServiceModelDto> findServiceThatHaveApi(String apiName);
}
