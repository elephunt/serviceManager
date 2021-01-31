package com.margolin.project.servicemanager.app.main.repository;

import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends MongoRepository<ServiceModelDto,String> {

    @Query(value = "{'ap.name' : ?0, 'ap.serviceName' : ?1 }")
    List<ServiceModelDto> findByApiNameAndServiceName(String apiName, String serviceName);

    @Query(value = "{'ap.name' : { $regex :?0, $options : 'i' }}")
    List<ServiceModelDto> findByApiName(String apiName);
}
