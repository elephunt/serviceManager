package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import org.springframework.stereotype.Service;

@Service
public class ServiceManager implements IServiceManager {


   @Override
   public ServiceModelDto getServiceModel(String id){
       return null;
   }

   @Override
   public ServiceModelDto saveServiceModel(ServiceModelDto serviceModelDto){
       return null;
   }


}
