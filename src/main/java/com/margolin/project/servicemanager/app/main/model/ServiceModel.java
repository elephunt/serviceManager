package com.margolin.project.servicemanager.app.main.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.awt.dnd.DnDConstants;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ServiceModel {

    private String name;
    private String id;
    private List<ApiModel> apis;
    private String version;
    private List<DbModel> dbModels;

    public ServiceModel(@NotEmpty String name,@NotEmpty List<ApiModel> apis,@NotEmpty String version) {
        this.name = name;
        this.apis = apis;
        this.version = version;
    }
}
