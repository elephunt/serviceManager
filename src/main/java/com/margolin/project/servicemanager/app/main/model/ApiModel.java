package com.margolin.project.servicemanager.app.main.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ApiModel {

    public ApiModel(@NotEmpty String name, @NotEmpty String path, @NotEmpty String action, @NotEmpty String serviceName, @NotEmpty String version) {
        this.name = name;
        this.path = path;
        this.action = action;
        this.serviceName = serviceName;
        this.version = version;
    }

    @NotEmpty
    private String name;
    @NotEmpty
    private String path;
    @NotEmpty
    private String action;
    @NotEmpty
    private String serviceName;
    private String payload;
    @NotEmpty
    private String version;
    private String initService;
    private String destService;
}
