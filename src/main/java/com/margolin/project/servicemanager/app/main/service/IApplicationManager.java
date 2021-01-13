package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;

public interface IApplicationManager {

    ServiceModelDto getServiceById(String id);

    ServiceModelDto createServiceModel(ServiceModelDto serviceModelDto);
}
