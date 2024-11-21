package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

public class UserMapper {
    // convert User JPA entity into UserDto
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getEmail()
        );
        return userDto;
    }
    // convert UserDto into User JPA entity
    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getLastName(),
                userDto.getFirstName(),
                userDto.getEmail()
        );
        return user;
    }
}
