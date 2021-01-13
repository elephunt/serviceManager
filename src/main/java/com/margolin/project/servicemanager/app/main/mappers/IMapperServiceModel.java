package com.margolin.project.servicemanager.app.main.mappers;


import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.model.ServiceModel;
import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring",
        implementationName = "mapperServiceModelImpl",
        uses = {IMapperApiModel.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public abstract class IMapperServiceModel {


    public abstract ServiceModel toModel(ServiceModelDto modelDto);

    public  abstract  ServiceModelDto toDto(ServiceModel serviceModel);

    @AfterMapping
    protected void setResource(ServiceModel serviceModelRequest,@MappingTarget ServiceModelDto serviceModelDto) {
        serviceModelDto.setServiceModelRequest(serviceModelRequest);
    }


}
