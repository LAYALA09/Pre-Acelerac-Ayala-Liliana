package com.alkemy.disney.disney.exception;
@SuppressWarnings("serial")
public class ParamNotFound extends RuntimeException{
    /**
     * Exception related to searches which returns nothing
     * @param message*/

    public ParamNotFound(String error) {
        super(error);
    }
}
