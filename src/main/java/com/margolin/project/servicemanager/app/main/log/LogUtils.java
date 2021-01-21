package com.margolin.project.servicemanager.app.main.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class LogUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static void  log(Logger logger, String message, Object object){
        if(logger.isDebugEnabled()){
            logger.debug("Message : {}",message);
            try {
                logger.debug("Object : {}",objectMapper.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                log.error("Failed to serialize object : {}",e.getMessage());
            }
        }
    }

    public static String toJson(Object object){
        try {
           return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
           log.error("Failed to serialize object : {}",e.getMessage());
        }
        return "";
    }
}
