package com.benevenuto.parkingSystem.exceptions;

import com.benevenuto.parkingSystem.domain.ExceptionMessage;

public class VeiculoNotFoundException extends RuntimeException{

    public VeiculoNotFoundException(String message)
    {
        super(message);
    }
}
