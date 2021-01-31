package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import com.margolin.project.servicemanager.app.main.repository.ServiceRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
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

    @Override
    public ServiceModelDto updateModel(ServiceModelDto updatedServiceModel) {
         return this.serviceRepository.save(updatedServiceModel);
    }

    @Override
    public List<ServiceModelDto> findServiceThatHaveApi(String apiName){
       return this.serviceRepository.findByApiName(apiName);
    }

    @Override
    public List<ServiceModelDto> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, 25);
        List<ServiceModelDto> content = this.serviceRepository.findAll(pageRequest).getContent();
        return content;
    }
}
