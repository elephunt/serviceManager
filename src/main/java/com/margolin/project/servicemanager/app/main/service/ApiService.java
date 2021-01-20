package com.margolin.project.servicemanager.app.main.service;

import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;

import java.util.List;

public interface ApiService {
    List<ApiModelDto> getApiByName(String name);

    List<ApiModelDto> saveApis(List<ApiModelDto> apis);
}
