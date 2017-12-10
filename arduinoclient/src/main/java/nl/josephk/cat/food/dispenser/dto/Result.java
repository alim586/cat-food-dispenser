package nl.josephk.cat.food.dispenser.dto;

import java.io.Serializable;


public class Result implements Serializable{

    private Long responseTime;
    private String responseMessage;


    public Long getResponseTime() {
        return responseTime;
    }

    public Result setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public Result setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }
}
