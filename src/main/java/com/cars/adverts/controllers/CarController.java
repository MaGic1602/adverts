package com.cars.adverts.controllers;

import com.cars.adverts.dto.CarAdvertDTO;
import com.cars.adverts.models.CarAdvert;
import com.cars.adverts.services.CarService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping(path="/car")
public class CarController {
    private final CarService carService;


    //get all car adverts with sorting
    @GetMapping(path = "/adverts")
    public List<CarAdvert> getCarAdverts(@RequestParam(value = "sortBy", required = false) String sortBy){ //add sort
        if(sortBy==null){
            return carService.fetchCarAdvertList();
        } else if (sortBy.isBlank()) {
            return carService.fetchCarAdvertList("id");

        } else{
            return carService.fetchCarAdvertList(sortBy.toLowerCase());
        }
    }
    //get car advert by id
    @GetMapping(path = "/adverts/{id}")
    public Optional<CarAdvert> getCarAdvertByID(@PathVariable("id") Long carId){
        return carService.fetchCarAdvertByID(carId);
    }
   //save car advert
    @PostMapping(path="/adverts")
   public ResponseEntity saveCarAdvert(@Valid @RequestBody CarAdvert carAdvert) {
        carService.saveCarAdvert(carAdvert);
        return new ResponseEntity(carAdvert,HttpStatus.CREATED);

   }

   //modifies an existing car advert
@PutMapping(path="/adverts/{id}")
    public CarAdvertDTO modifyCarAdvert(@PathVariable("id") Long carId, @RequestBody CarAdvertDTO carAdvertDTO){
        carService.modifyCarAdvert(carAdvertDTO);
return carAdvertDTO;
}

@DeleteMapping(path="/adverts/{id}")
    public ResponseEntity deleteCarAdvert(@PathVariable("id") Long carId){
        carService.deleteCarAdvert(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
