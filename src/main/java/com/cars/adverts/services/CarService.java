package com.cars.adverts.services;

import com.cars.adverts.dto.CarAdvertDTO;
import com.cars.adverts.dto.CarAdvertMapper;
import com.cars.adverts.models.CarAdvert;
import com.cars.adverts.repositories.CarAdvertRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;


import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {

    private final CarAdvertRepository carAdvertRepository;
    private CarAdvertMapper carAdvertMapper;

    CarAdvertMapper INSTANCE = Mappers.getMapper(CarAdvertMapper.class);
    //get all car adverts
    public List<CarAdvert> fetchCarAdvertList() {
        return carAdvertRepository.findAll();
    }

    //get all car adverts with sorting
    public List<CarAdvert> fetchCarAdvertList(String sortBy) {
        return carAdvertRepository.findAll(Sort.by(Sort.Direction.DESC,sortBy));
    }

    //get car adverts by id
    public Optional<CarAdvert> fetchCarAdvertByID(Long carId) {
        return Optional.ofNullable(carAdvertRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("No car advert with given id was found.")));

    }

    //save new car advert
    public CarAdvert saveCarAdvert(CarAdvert carAdvert) {
        if(carAdvertRepository.existsById(carAdvert.getId().longValue()))
        {
            throw new RuntimeException("Entity with given id already exists");
        }
        else{
            return carAdvertRepository.save(carAdvert);
        }

    }

    //modify an existing car advert
    public Optional<CarAdvert> modifyCarAdvert(CarAdvertDTO carAdvertDTO, Long carId) {
        CarAdvert carAdvert = carAdvertRepository.findById(carId).get();
        if (carAdvertDTO.getId() != null) {
            if (carAdvertDTO.getId() > 0) {
                carAdvertRepository.deleteById(carId);
            } else {
                throw new IllegalArgumentException("Id must be a positive number");
            }
        }
        carAdvertMapper.updateCarAdvertFromDto(carAdvertDTO, carAdvert);
        carAdvertRepository.save(carAdvert);
        Long newCarId = carAdvert.getId();
        return carAdvertRepository.findById(newCarId);
    }
//delete car advert with given id
    public void deleteCarAdvert(Long carId) {
       if(carAdvertRepository.existsById(carId)){
           carAdvertRepository.deleteById(carId);
       }
       else {
           throw new EntityNotFoundException("No car advert with given id was found.");
       }
    }
}