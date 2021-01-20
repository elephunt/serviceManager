package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationManager implements IApplicationManager {

    final IServiceManager serviceManager;
    final ApiService apiService;
    public ApplicationManager(IServiceManager serviceManager, ApiService apiService) {
        this.serviceManager = serviceManager;
        this.apiService = apiService;
    }

    @Override
    public ServiceModelDto getServiceById(String id) {
        return serviceManager.getServiceModel(id);
    }

    @Override
    public ServiceModelDto createServiceModel(ServiceModelDto serviceModelDto) {
        List<ApiModelDto> updatedApis = this.apiService.saveApis(serviceModelDto.getApis());
        serviceModelDto.setApis(updatedApis);
        return serviceManager.saveServiceModel(serviceModelDto);
    }

    @Override
    public ServiceModelDto updateService(ServiceModelDto updatedServiceModel) {
        return  serviceManager.updateModel(updatedServiceModel);
    }

    @Override
    public List<ApiModelDto> getApiByName(String name) {
        return  this.apiService.getApiByName(name);
    }
}
