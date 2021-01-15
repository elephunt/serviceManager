package com.margolin.project.servicemanager.app.main.exceptions;

import lombok.Data;

@Data
public class PatchException extends Exception{

    private String patchError;
}
