package com.br.schoolreyfowlogin.model.mapper;

import com.br.schoolreyfowlogin.model.User;
import com.br.schoolreyfowlogin.model.dto.UserDto;
import com.br.schoolreyfowlogin.model.dto.UserResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class ModelDaoOMapper {

    public abstract User create(UserDto userDto);

    abstract User userResponseDTOToUser(UserResponseDTO userResponseDTO);

    public abstract UserResponseDTO userToUserResponseDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract void updateUserFromUserResponseDTO(UserResponseDTO userResponseDTO, @MappingTarget User user);
}
