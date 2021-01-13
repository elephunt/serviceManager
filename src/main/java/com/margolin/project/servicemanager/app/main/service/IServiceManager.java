package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;

public interface IServiceManager {
    ServiceModelDto getServiceModel(String id);

    ServiceModelDto saveServiceModel(ServiceModelDto serviceModelDto);
}
