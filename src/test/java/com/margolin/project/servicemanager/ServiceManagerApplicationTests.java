package com.margolin.project.servicemanager;

import com.margolin.project.servicemanager.app.main.controller.ServiceManagerController;
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

@SpringBootTest
@ActiveProfiles("test")
class ServiceManagerApplicationTests {

    @Autowired
    private ServiceManagerController managerController;
    @Test
    void contextLoads() {
    }

    @Test
    public void createService(){
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
        ResponseEntity<ServiceModel> serviceById = this.managerController.getServiceById(serviceModel.getId());
        ResponseEntity<ServiceModel> flowManagerById = this.managerController.getServiceById(flowManagerServiceResponseBody.getId());
        Assertions.assertEquals("serviceManager",serviceById.getBody().getName());
        Assertions.assertEquals("flowManager",flowManagerById.getBody().getName());
    }
}
