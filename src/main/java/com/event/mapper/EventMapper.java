package com.event.mapper;

import com.event.dto.EventRequest;
import com.event.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdBy",ignore = true)
    EventEntity toEventEntity(EventRequest eventRequest);

    @Mapping(target = "userId", source = "createdBy.id")
    @Mapping(target = "eventId",source = "id")
    EventRequest toDto(EventEntity eventEntity);


    List<EventRequest> toDtoList(List<EventEntity> eventEntities);
}

