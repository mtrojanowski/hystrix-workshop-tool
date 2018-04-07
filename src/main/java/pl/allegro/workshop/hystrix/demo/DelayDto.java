package pl.allegro.workshop.hystrix.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DelayDto {
    private final int value;

    @JsonCreator
    public DelayDto(@JsonProperty("value") int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
