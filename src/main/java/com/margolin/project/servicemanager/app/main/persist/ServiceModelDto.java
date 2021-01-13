package com.margolin.project.servicemanager.app.main.persist;

import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.model.ServiceModel;
import lombok.Data;

import java.util.List;

@Data
public class ServiceModelDto {
    private String name;
    private List<ServiceModelDto> services;
    private String id;
    private List<ApiModelDto> apis;
    private String version;
    private ServiceModel serviceModelRequest;
}
