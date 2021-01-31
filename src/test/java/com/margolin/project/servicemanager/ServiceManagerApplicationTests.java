package com.margolin.project.servicemanager;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.margolin.project.servicemanager.app.main.controller.ServiceManagerController;
import com.margolin.project.servicemanager.app.main.exceptions.GeneralException;
import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.model.ServiceModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class ServiceManagerApplicationTests {

    @Autowired
    private ServiceManagerController managerController;
    @Test
    void contextLoads() {
    }

    private   boolean created = false;
    private String idServiceManager;
    private String idFlowManager;

    @BeforeEach
    public void insertData(){
        if(!created){
            ApiModel apiModel = new ApiModel("getService","/","GET","serviceManager","0");
            apiModel.setPath("/");
            apiModel.setInitService("serviceManager");
            ServiceModel serviceManager = new ServiceModel("serviceManager",Collections.singletonList(apiModel),"0");
            ApiModel apiModel1 = new ApiModel("getFlow","/","GET","flowManager","0");
            ServiceModel flowManager = new ServiceModel("flowManager", Arrays.asList(apiModel1),"0");
            ResponseEntity<ServiceModel> serviceManagerServiceResponse = this.managerController.createService(serviceManager);
            ResponseEntity<ServiceModel> flowManagerServiceResponse = this.managerController.createService(flowManager);
            Assertions.assertNotNull(serviceManagerServiceResponse.getBody());
            Assertions.assertNotNull(flowManagerServiceResponse.getBody());
            ServiceModel serviceModel = serviceManagerServiceResponse.getBody();
            ServiceModel flowManagerServiceResponseBody = flowManagerServiceResponse.getBody();
            Assertions.assertEquals("serviceManager",serviceModel.getName());
            Assertions.assertEquals("flowManager",flowManagerServiceResponseBody.getName());
            Assertions.assertEquals(1,serviceModel.getApis().size());
            idServiceManager = serviceModel.getId();
            idFlowManager = flowManagerServiceResponseBody.getId();
            created = true;
        }
    }

    @Test
    public void createService(){
        ResponseEntity<ServiceModel> serviceById = this.managerController.getServiceById(idServiceManager);
        ResponseEntity<ServiceModel> flowManagerById = this.managerController.getServiceById(idFlowManager);
        Assertions.assertEquals("serviceManager",serviceById.getBody().getName());
        Assertions.assertEquals("flowManager",flowManagerById.getBody().getName());
    }

    @Test
    public void updateService() throws IOException, GeneralException {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
        ObjectNode replaceVersion = JsonNodeFactory.instance.objectNode();
        replaceVersion.put("op","replace");
        replaceVersion.put("path","/version");
        replaceVersion.put("value","4");
        arrayNode.add(replaceVersion);
        JsonPatch patch = JsonPatch.fromJson(arrayNode);
        ResponseEntity<ServiceModel> serviceModelResponseEntity = this.managerController.updateService(patch,  idFlowManager);
        Assertions.assertNotNull(serviceModelResponseEntity.getBody());
        ServiceModel responseEntityBody = serviceModelResponseEntity.getBody();
        Assertions.assertEquals(responseEntityBody.getVersion(),"4");
        ObjectNode addApi = JsonNodeFactory.instance.objectNode();
        ObjectNode value = JsonNodeFactory.instance.objectNode();
        value.put("name","getService");
        value.put("path","");
        value.put("action","GET");
        value.put("serviceName","serviceManager");
        value.put("version","1");
        value.put("initService","serviceManager");
        value.put("destService","serviceManager");
        addApi.put("op","add");
        addApi.put("path","/apis/-");
        addApi.put("value",value);
        ArrayNode addApiArray = JsonNodeFactory.instance.arrayNode();
        addApiArray.add(addApi);
        JsonPatch addAdiPatchPayload = JsonPatch.fromJson(addApiArray);
        ResponseEntity<ServiceModel> patchedServiceModel = this.managerController.updateService(addAdiPatchPayload, idServiceManager);
        Assertions.assertNotNull(patchedServiceModel.getBody());
        ServiceModel body = patchedServiceModel.getBody();
        Assertions.assertEquals(2,body.getApis().size());
    }

    @Test
    public void getApi(){
        ResponseEntity<List<ApiModel>> api = this.managerController.getApi("get");
        Assertions.assertNotNull(api);
        List<ApiModel> apis = api.getBody();
        Assertions.assertFalse(CollectionUtils.isEmpty(apis));
        boolean found = false;
        for (ApiModel apiModel : apis) {
            found = found || apiModel.getName().contains("get");
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void getAll(){
        ResponseEntity<List<ServiceModel>> all = this.managerController.getAll(0);
        List<ServiceModel> body = all.getBody();
        Assertions.assertFalse(CollectionUtils.isEmpty(body));
    }
}
