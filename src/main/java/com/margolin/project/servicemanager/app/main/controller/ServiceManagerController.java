package com.margolin.project.servicemanager.app.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.margolin.project.servicemanager.app.main.exceptions.PatchException;
import com.margolin.project.servicemanager.app.main.mappers.IMapperApiModel;
import com.margolin.project.servicemanager.app.main.mappers.IMapperPatchModel;
import com.margolin.project.servicemanager.app.main.mappers.IMapperServiceModel;
import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.model.ServiceModel;
import com.margolin.project.servicemanager.app.main.persist.ApiModelDto;
import com.margolin.project.servicemanager.app.main.persist.ServiceModelDto;
import com.margolin.project.servicemanager.app.main.service.IApplicationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/service/v1/")
public class ServiceManagerController {

    private final IApplicationManager applicationManager;
    private final IMapperServiceModel mapperServiceModel;
    private final IMapperPatchModel mapperPatchModel;
    private final ObjectMapper objectMapper;
    private final IMapperApiModel mapperApiModel;

    public ServiceManagerController(IApplicationManager applicationManager, IMapperServiceModel mapperServiceModel, IMapperPatchModel mapperPatchModel, ObjectMapper objectMapper, IMapperApiModel mapperApiModel) {
        this.applicationManager = applicationManager;
        this.mapperServiceModel = mapperServiceModel;
        this.mapperPatchModel = mapperPatchModel;
        this.objectMapper = objectMapper;
        this.mapperApiModel = mapperApiModel;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceModel> getServiceById(@PathVariable String id){
        ServiceModelDto service = applicationManager.getServiceById(id);
        ServiceModel serviceModel = mapperServiceModel.toModel(service);
        return ResponseEntity.ok(serviceModel);
    }

    @PostMapping("/")
    public ResponseEntity<ServiceModel> createService(@RequestBody ServiceModel serviceModel){
        ServiceModelDto serviceModelDto = mapperServiceModel.toDto(serviceModel);
        ServiceModelDto createServiceModelDto = this.applicationManager.createServiceModel(serviceModelDto);
        ServiceModel createdModel = mapperServiceModel.toModel(createServiceModelDto);
        return ResponseEntity.ok(createdModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceModel> updateService(@RequestBody JsonPatch patchModels, @PathVariable String id) throws PatchException {
        ServiceModelDto dto = patchServiceModel(patchModels, id);
        ServiceModelDto updatedResult = this.applicationManager.updateService(dto);
        ServiceModel modelDto = this.mapperServiceModel.toModel(updatedResult);
        return ResponseEntity.ok(modelDto);
    }

    @GetMapping("/apis")
    public ResponseEntity<List<ApiModel>> getApi(@RequestParam String name){
       List<ApiModelDto> models =  this.applicationManager.getApiByName(name);
        List<ApiModel> apiModels = mapperApiModel.toModel(models);
        return ResponseEntity.ok(apiModels);
    }

    private ServiceModelDto patchServiceModel(@RequestBody JsonPatch patchModels, @PathVariable String id) throws PatchException {
        ServiceModelDto serviceById = this.applicationManager.getServiceById(id);
        ServiceModel serviceModel = this.mapperServiceModel.toModel(serviceById);
        ServiceModel patchedModel = null;
        try {
            patchedModel = this.applyPatchToCustomer(patchModels, serviceModel);
        } catch (JsonPatchException | JsonProcessingException e) {
            PatchException patchException = new PatchException();
            patchException.setPatchError(e.getMessage());
            throw patchException;
        }
        return this.mapperServiceModel.toDto(patchedModel);
    }


    private ServiceModel applyPatchToCustomer(
            JsonPatch jsonPatch, ServiceModel serviceModel) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonNode = objectMapper.convertValue(serviceModel, JsonNode.class);
        JsonNode result = jsonPatch.apply(jsonNode);
        return objectMapper.treeToValue(result, ServiceModel.class);
    }

}
