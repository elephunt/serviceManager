package com.margolin.project.servicemanager.app.main.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ServiceModel {

    private String name;
    private String id;
    private List<ApiModel> apis = new LinkedList<>();
    private String version;
}
