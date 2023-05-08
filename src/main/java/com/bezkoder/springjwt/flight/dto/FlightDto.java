package com.bezkoder.springjwt.flight.dto;

import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientRequestForFlightDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class FlightDto {

    private String departure;

    private String destination;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departureTime;

    private String aClassEnum;

   // private ClientRequestForFlightDto clientRequestForFlightDtos ;

}
