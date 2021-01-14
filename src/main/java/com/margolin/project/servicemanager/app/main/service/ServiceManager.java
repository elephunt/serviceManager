package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import com.margolin.project.servicemanager.app.main.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceManager implements IServiceManager {

    final
    ServiceRepository serviceRepository;

    public ServiceManager(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
   public ServiceModelDto getServiceModel(String id){
        Optional<ServiceModelDto> serviceModelDto = serviceRepository.findById(id);
        return serviceModelDto.orElse(null);
   }

   @Override
   public ServiceModelDto saveServiceModel(ServiceModelDto serviceModelDto){
       ServiceModelDto modelDto = serviceRepository.save(serviceModelDto);
       return modelDto;
   }


}