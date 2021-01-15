package com.margolin.project.servicemanager.app.main.mappers;

import com.margolin.project.servicemanager.app.main.model.PatchModel;
import com.margolin.project.servicemanager.app.main.persist.PatchModelDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring",
        implementationName = "mapperPatchModelImpl",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public abstract class IMapperPatchModel {

   public abstract List<PatchModelDto> toDto(List<PatchModel> patchModel);

   public abstract PatchModelDto toDto(PatchModel patchModel);

}
