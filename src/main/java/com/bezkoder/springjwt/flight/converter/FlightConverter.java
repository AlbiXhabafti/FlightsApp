package com.bezkoder.springjwt.flight.converter;

import com.bezkoder.springjwt.account.converter.UserConverter;
import com.bezkoder.springjwt.client.converter.ClientConverter;
import com.bezkoder.springjwt.flight.dto.FlightDto;
import com.bezkoder.springjwt.flight.dto.FlightDtoResponse;
import com.bezkoder.springjwt.flight.enums.ClassEnum;
import com.bezkoder.springjwt.flight.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightConverter {
    public static FlightDtoResponse toDto(Flight flight){
        FlightDtoResponse dto = new FlightDtoResponse();
        dto.setDeparture(flight.getDeparture());
        dto.setDestination(flight.getDestination());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setAClassEnum(String.valueOf(flight.getAClassEnum()));
      //  dto.setClientResponseDtos(ClientConverter.toDtoList(flight.getClient()));;
        return dto;
    }
    public static List<FlightDtoResponse>toListDto(List<Flight>flightList){
        List<FlightDtoResponse>dtoList = new ArrayList<>();
        for (Flight flight:flightList){
            dtoList.add(toDto(flight));
        }
        return dtoList;
    }
    public static Flight toEntity(FlightDto dto){
        Flight flight = new Flight();
        flight.setDeparture(dto.getDeparture());
        flight.setDestination(dto.getDestination());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setAClassEnum(ClassEnum.valueOf(dto.getAClassEnum()));
        // flight.setClient(ClientConverter.toEntityList(dto.getClientDtoList()));

        return flight;
    }
}
