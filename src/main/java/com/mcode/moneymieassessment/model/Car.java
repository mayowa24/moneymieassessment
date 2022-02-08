package com.mcode.moneymieassessment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
public class Car {
    private String vin;
    private String brand;
    private int year;
    private String color;
}
