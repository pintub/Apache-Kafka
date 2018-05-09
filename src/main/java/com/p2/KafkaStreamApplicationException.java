package com.p2;

public class KafkaStreamApplicationException extends Exception{

    private String errorMessage;

    public KafkaStreamApplicationException(Exception e){
        super(e);
    }

    public KafkaStreamApplicationException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public KafkaStreamApplicationException(String errorMessage, Exception e){
        super(errorMessage, e);
        this.errorMessage = errorMessage;
    }
}
