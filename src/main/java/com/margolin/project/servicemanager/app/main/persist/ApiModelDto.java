package com.margolin.project.servicemanager.app.main.persist;

import com.margolin.project.servicemanager.app.main.model.ApiModel;
import lombok.Data;

@Data
public class ApiModelDto {

    private ApiModel apiModelRequest;
    private String name;
    private String path;
    private String action;
    private String serviceName;
    private String payload;
    private String version;
}
