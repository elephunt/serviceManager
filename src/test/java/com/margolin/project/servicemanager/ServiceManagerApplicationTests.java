package com.margolin.project.servicemanager;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonpatch.AddOperation;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.margolin.project.servicemanager.app.main.controller.ServiceManagerController;
import com.margolin.project.servicemanager.app.main.exceptions.PatchException;
import com.margolin.project.servicemanager.app.main.model.ApiModel;
import com.margolin.project.servicemanager.app.main.model.ServiceModel;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

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
            ServiceModel serviceManager = new ServiceModel();
            serviceManager.setName("serviceManager");
            serviceManager.setVersion("1");
            ServiceModel flowManager = new ServiceModel();
            flowManager.setName("flowManager");
            flowManager.setVersion("2");
            ResponseEntity<ServiceModel> serviceManagerServiceResponse = this.managerController.createService(serviceManager);
            ResponseEntity<ServiceModel> flowManagerServiceResponse = this.managerController.createService(flowManager);
            Assertions.assertNotNull(serviceManagerServiceResponse.getBody());
            Assertions.assertNotNull(flowManagerServiceResponse.getBody());
            ServiceModel serviceModel = serviceManagerServiceResponse.getBody();
            ServiceModel flowManagerServiceResponseBody = flowManagerServiceResponse.getBody();
            Assertions.assertEquals("serviceManager",serviceModel.getName());
            Assertions.assertEquals("flowManager",flowManagerServiceResponseBody.getName());
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
    public void updateService() throws IOException, PatchException {
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
    }
}
