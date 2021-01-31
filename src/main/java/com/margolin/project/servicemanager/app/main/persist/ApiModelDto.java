package com.margolin.project.servicemanager.app.main.persist;

import com.margolin.project.servicemanager.app.main.model.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "services")
public class ApiModelDto {

    @Transient
    private ApiModel apiModelRequest;
    @Indexed
    private String name;
    private String path;
    private String action;
    @Indexed(unique = true)
    private String serviceName;
    private String payload;
    private String version;
    private String initService;
    private String dest;
    private LocalDate addedOn;

}
