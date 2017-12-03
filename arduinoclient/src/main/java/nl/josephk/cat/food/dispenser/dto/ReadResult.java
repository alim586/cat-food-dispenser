package nl.josephk.cat.food.dispenser.dto;

import java.io.Serializable;


public class ReadResult implements Serializable{

    String pin;
    String pinValue;
    Long responseTime;

    public String getPin() {
        return pin;
    }

    public ReadResult setPin(String pin) {
        this.pin = pin;
        return this;
    }

    public String getPinValue() {
        return pinValue;
    }

    public ReadResult setPinValue(String pinValue) {
        this.pinValue = pinValue;
        return this;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public ReadResult setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
        return this;
    }
}
