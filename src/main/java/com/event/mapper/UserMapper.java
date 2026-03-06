package com.event.mapper;

import com.event.dto.UserRequest;
import com.event.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

     @Mapping(target = "id",ignore = true)
     UserEntity toEntity(UserRequest userRequest);

     @Mapping(target = "userId",source = "id")
     UserRequest toDto(UserEntity userEntity);

     List<UserRequest> toDtoList(List<UserEntity> userEntityList);
}
