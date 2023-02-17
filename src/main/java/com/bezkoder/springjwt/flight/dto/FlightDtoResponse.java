package com.bezkoder.springjwt.flight.dto;

import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientResponseDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import net.bytebuddy.implementation.bytecode.ShiftRight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class FlightDtoResponse {

   private String departure;

    private String destination;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departureTime;

    private String aClassEnum;

   // private List<ClientResponseDto> clientResponseDtos = new ArrayList<>();

}
