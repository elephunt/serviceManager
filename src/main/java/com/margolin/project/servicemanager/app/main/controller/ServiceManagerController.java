package com.margolin.project.servicemanager.app.main.controller;

import com.margolin.project.servicemanager.app.main.mappers.IMapperServiceModel;
import com.margolin.project.servicemanager.app.main.model.ServiceModel;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import com.margolin.project.servicemanager.app.main.service.IApplicationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/service/v1/")
public class ServiceManagerController {

    private final IApplicationManager applicationManager;
    private final IMapperServiceModel mapperServiceModel;

    public ServiceManagerController(IApplicationManager applicationManager, IMapperServiceModel mapperServiceModel) {
        this.applicationManager = applicationManager;
        this.mapperServiceModel = mapperServiceModel;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceModel> getServiceById(@PathVariable String id){
        ServiceModelDto service = applicationManager.getServiceById(id);
        ServiceModel serviceModel = mapperServiceModel.toModel(service);
        return ResponseEntity.ok(serviceModel);
    }

    @PostMapping("/")
    public ResponseEntity<ServiceModel> createService(@RequestBody ServiceModel serviceModel){
        ServiceModelDto serviceModelDto = mapperServiceModel.toDto(serviceModel);
        ServiceModelDto createServiceModelDto = this.applicationManager.createServiceModel(serviceModelDto);
        ServiceModel createdModel = mapperServiceModel.toModel(createServiceModelDto);
        return ResponseEntity.ok(createdModel);
    }
}
