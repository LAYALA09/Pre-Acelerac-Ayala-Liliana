package com.alkemy.disney.disney.exception;

public class ParamNotFound extends RuntimeException{
    /**
     * Exception related to searches which returns nothing
     * * @param message*/

    public ParamNotFound(String message) {
        super(message);
    }
}
