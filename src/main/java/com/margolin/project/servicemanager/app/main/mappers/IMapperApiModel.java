package com.margolin.project.servicemanager.app.main.mappers;

import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring",
        implementationName = "mapperApiModelImpl",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public abstract class IMapperApiModel {

    public abstract ApiModel toModel(ApiModelDto modelDto);

    public  abstract  ApiModelDto toDto(ApiModel apiModel);

    @AfterMapping
    protected void setResource(ApiModel apiModel,@MappingTarget ApiModelDto apiModelDto) {
        apiModelDto.setApiModelRequest(apiModel);
    }

}
