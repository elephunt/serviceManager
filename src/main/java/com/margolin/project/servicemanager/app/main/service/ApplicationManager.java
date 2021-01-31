package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.log.LogUtils;
import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApplicationManager implements IApplicationManager {

    final IServiceManager serviceManager;
    final ApiService apiService;
    public ApplicationManager(IServiceManager serviceManager, ApiService apiService) {
        this.serviceManager = serviceManager;
        this.apiService = apiService;
    }

    @Override
    public ServiceModelDto getServiceById(String id) {
        ServiceModelDto serviceModel = serviceManager.getServiceModel(id);
        return serviceModel;
    }

    @Override
    public ServiceModelDto createServiceModel(ServiceModelDto serviceModelDto) {
        LogUtils.log(log,"Saving model ",serviceModelDto);
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

    @Override
    public List<ServiceModelDto> getAllServices(int page) {
        List<ServiceModelDto> services  = this.serviceManager.getAll(page);
        return services;
    }
}
