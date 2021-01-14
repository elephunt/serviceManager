package com.margolin.project.servicemanager.app.main.persist;

import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.model.ServiceModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "services")
public class ServiceModelDto {
    @Id
    private String id;
    private String name;
    private List<ApiModelDto> apis;
    private String version;
    @Transient
    private ServiceModel serviceModelRequest;
}
