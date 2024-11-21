package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.AutoUserMapper;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.javaguides.springboot.mapper.UserMapper.mapToUser;
import static net.javaguides.springboot.mapper.UserMapper.mapToUserDto;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        // convert userDto into User jpa entity

        //  method 1:  UserMapper
        // User user = mapToUser(userDto);

        //  method 2:  ModelMapper
        // User user = modelMapper.map(userDto, User.class);

        //  method 3:  MapStruct
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser  = userRepository.save(user);

        // convert User JPA to UserDto

        //  method 1:  UserMapper
        // UserDto savedUserDto= mapToUserDto(savedUser);

        //  method 2:  ModelMapper
        // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        //  method 3:  MapStruct
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        User user = optionalUser;
        // UserDto userDto = mapToUserDto(user);
        // UserDto userDto = modelMapper.map(user, UserDto.class);
        UserDto userDto = AutoUserMapper.MAPPER.mapToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        // return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
//        return users.stream().map(
//                (
//                        user -> modelMapper.map(user, UserDto.class))
//        ).collect(Collectors.toList());
        return users.stream().map(
                (
                        user -> AutoUserMapper.MAPPER.mapToUserDto(user))
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = mapToUser(userDto);
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
