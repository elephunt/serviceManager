package com.margolin.project.servicemanager.app.main.repository;

import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends MongoRepository<ApiModelDto,String> {

    List<ApiModelDto> findByNameLike(String name);
}
