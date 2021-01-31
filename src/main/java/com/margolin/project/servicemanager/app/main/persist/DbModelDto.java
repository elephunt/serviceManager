package com.margolin.project.servicemanager.app.main.persist;


import lombok.Data;

@Data
public class DbModelDto {
    private String url;
    private String name;
    private String type;
}
