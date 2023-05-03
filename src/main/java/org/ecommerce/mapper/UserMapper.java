package org.ecommerce.mapper;

import org.ecommerce.domain.User;
import org.ecommerce.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getUserLogin(),
                userDto.getUserEmail(),
                userDto.getUserPassword()
        );
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUserLogin(),
                user.getUserPassword(),
                user.getUserEmail(),
                user.getSignUpDate()
        );
    }

    public List<String> mapToEmailList(List<User> users) {
        return users.stream().map(User::getUserEmail).collect(Collectors.toList());
    }
    public List<UserDto> mapToUserDtoList(List<User> users){
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }
}