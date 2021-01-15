package com.margolin.project.servicemanager.app.main.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ServiceModelDto updateService(ServiceModelDto updatedServiceModel) {
        return  serviceManager.updateModel(updatedServiceModel);
    }
}
