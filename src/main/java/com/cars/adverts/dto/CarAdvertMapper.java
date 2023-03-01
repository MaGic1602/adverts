package com.cars.adverts.dto;

import com.cars.adverts.models.CarAdvert;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CarAdvertMapper {
CarAdvertMapper MAPPER =Mappers.getMapper(CarAdvertMapper.class);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarAdvertFromDto(CarAdvertDTO carAdvertDTO, @MappingTarget CarAdvert carAdvert);

    CarAdvertDTO mapToCarAdvertDTO(CarAdvert carAdvert);
    CarAdvert mapToCarAdvert(CarAdvertDTO carAdvertDTO);
}
