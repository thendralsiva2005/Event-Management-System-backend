package com.event.mapper;

import com.event.dto.EventRequest;
import com.event.entity.EventBookingEntity;
import com.event.entity.EventEntity;
import com.event.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventBookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "event", source = "eventEntity")
    EventBookingEntity toEntity(UserEntity userEntity, EventEntity eventEntity);

    EventRequest toDto(EventBookingEntity eventBookingEntity);
}