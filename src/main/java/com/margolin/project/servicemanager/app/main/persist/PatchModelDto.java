package com.margolin.project.servicemanager.app.main.persist;

import lombok.Data;

@Data
public class PatchModelDto {
    String op;
    String path;
    Object value;
}
