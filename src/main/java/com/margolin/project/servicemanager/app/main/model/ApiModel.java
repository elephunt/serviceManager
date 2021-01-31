package com.margolin.project.servicemanager.app.main.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String path;
    @NotEmpty
    @NotNull
    private String action;
    @NotEmpty
    @NotNull
    private String serviceName;
    private String payload;
    @NotEmpty
    @NotNull
    private String version;
    private String initService;
    private String destService;
    private LocalDate addedOn;
}
