package luiz.itauteste.entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class Transaction {

    @JsonProperty("value")
    private double value;

    @JsonProperty("time")
    private ZonedDateTime time;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Transaction(double value, ZonedDateTime time) {
        this.value = value;
        this.time = time;
    }

}
