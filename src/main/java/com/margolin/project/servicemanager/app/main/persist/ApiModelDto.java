package com.margolin.project.servicemanager.app.main.persist;

import com.margolin.project.servicemanager.app.main.model.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "apis")
public class ApiModelDto {

    @Transient
    private ApiModel apiModelRequest;
    private String name;
    private String path;
    private String action;
    private String serviceName;
    private String payload;
    private String version;
    @Id
    private String id;
}
