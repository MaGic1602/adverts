package com.cars.adverts.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CarAdvertDTO {

    public Long id;
    public String title;
    public String fuelType;
    public Integer price;
    public Boolean isNew;
    public Integer mileage;
    public Date firstRegistration;

}
