package com.margolin.project.servicemanager.app.main.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ServiceModel {

    private String name;
    private String id;
    private List<ApiModel> apis = new LinkedList<>();
    private String version;

    public ServiceModel(@NotEmpty String name,@NotEmpty List<ApiModel> apis,@NotEmpty String version) {
        this.name = name;
        this.apis = apis;
        this.version = version;
    }
}
