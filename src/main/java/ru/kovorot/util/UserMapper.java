package ru.kovorot.util;

import org.modelmapper.ModelMapper;
import ru.kovorot.dto.UserDTO;
import ru.kovorot.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}