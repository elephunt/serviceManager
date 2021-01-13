package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationManager implements IApplicationManager {

    final IServiceManager serviceManager;

    public ApplicationManager(IServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public ServiceModelDto getServiceById(String id) {
        return serviceManager.getServiceModel(id);
    }

    @Override
    public ServiceModelDto createServiceModel(ServiceModelDto serviceModelDto) {
        return serviceManager.saveServiceModel(serviceModelDto);
    }
}
