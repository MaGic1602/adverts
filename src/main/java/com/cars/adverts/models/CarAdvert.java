package com.cars.adverts.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_advert")
public class CarAdvert {
    @Id
    @Min(message = "ID must be a positive number", value = 0)
    @Column(name = "id", nullable = false, unique = true)
    @NotNull(message="Field ID can not be empty")
    private Long id;
    @Column(name = "title", nullable = false)
    @NotNull(message="Field title can not be empty")
    private String title;
    @Column(name = "fuel_type", nullable = false)
    @NotNull(message="Field fuel type can not be empty")
    private String fuelType;
    @Column(name = "price", nullable = false)
    @NotNull(message="Field price can not be empty")
    @Min(message = "Price cannot be negative", value = 0)
    private Integer price;
    @Column(name = "is_new", nullable = false)
    @NotNull(message="Field isNew can not be empty")
    private Boolean isNew;
    @Column(name = "mileage", nullable = true)
    private Integer mileage;
    @Column(name = "first_registration", nullable = true)
    private Date firstRegistration;


}