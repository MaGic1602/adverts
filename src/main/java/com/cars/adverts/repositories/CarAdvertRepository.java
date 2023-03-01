package com.cars.adverts.repositories;

import com.cars.adverts.models.CarAdvert;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarAdvertRepository extends JpaRepository<CarAdvert,Long> {
    CarAdvert findById(long id);


}
