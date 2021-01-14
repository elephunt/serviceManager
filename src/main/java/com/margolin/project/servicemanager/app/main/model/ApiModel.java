package com.margolin.project.servicemanager.app.main.model;

import lombok.Data;

@Data
public class ApiModel {

    private String name;
    private String path;
    private String action;
    private String serviceName;
    private String payload;
    private String version;
    private String initService;
    private String destService;
}
