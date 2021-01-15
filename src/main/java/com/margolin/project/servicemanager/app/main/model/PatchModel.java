package com.margolin.project.servicemanager.app.main.model;

import lombok.Data;

@Data
public class PatchModel {

    String op;
    String path;
    Object value;
}
