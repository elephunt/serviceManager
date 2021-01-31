package com.margolin.project.servicemanager.app.main.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.dnd.DnDConstants;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ServiceModel {

    @NotNull
    private String name;
    private String id;
    @NotNull
    private List<ApiModel> apis;
    @NotNull
    private String version;
    private List<DbModel> dbModels;

    public ServiceModel( String name,  List<ApiModel> apis,   String version) {
        this.name = name;
        this.apis = apis;
        this.version = version;
    }
}
