package com.margolin.project.servicemanager.app.main.persist;

import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.model.ServiceModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "services")
@CompoundIndexes( {
        @CompoundIndex(name = "apiName",def = "{'ap.name': 1"),
        @CompoundIndex(name= "apiServiceName",def = "{'ap.serviceName' : 1",unique = true),
        @CompoundIndex(name = "apis",def = "{'ap.name': 1 , 'ap.serviceName' : 1}"),
        @CompoundIndex(name = "apiIds",def = "{'ap.id' : 1 }",unique = true)
})

public class ServiceModelDto {
    @Id
    private String id;
    private String name;
    @Field("ap")
    private List<ApiModelDto> apis;
    private String version;
    @Transient
    private ServiceModel serviceModelRequest;

}
