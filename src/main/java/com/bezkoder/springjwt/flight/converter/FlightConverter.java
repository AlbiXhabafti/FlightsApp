package com.bezkoder.springjwt.flight.converter;

import com.bezkoder.springjwt.account.converter.UserConverter;
import com.bezkoder.springjwt.flight.dto.FlightDto;
import com.bezkoder.springjwt.flight.enums.ClassEnum;
import com.bezkoder.springjwt.flight.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightConverter {
    public static FlightDto toDto(Flight flight){
        FlightDto dto = new FlightDto();
        dto.setDeparture(flight.getDeparture());
        dto.setDestination(flight.getDestination());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setAClassEnum(String.valueOf(flight.getAClassEnum()));
        dto.setUserDtoList(UserConverter.toListDto(flight.getUsers()));
        return dto;
    }
    public static List<FlightDto>toListDto(List<Flight>flightList){
        List<FlightDto>dtoList = new ArrayList<>();
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
        flight.setUsers(UserConverter.toListEntity(dto.getUserDtoList()));

        return flight;
    }
}
