package com.margolin.project.servicemanager.app.main.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;

import java.util.List;

public interface IApplicationManager {

    ServiceModelDto getServiceById(String id);

    ServiceModelDto createServiceModel(ServiceModelDto serviceModelDto);

    ServiceModelDto updateService(ServiceModelDto updatedServiceModel);

}
