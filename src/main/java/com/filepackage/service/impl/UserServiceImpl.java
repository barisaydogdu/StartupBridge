package com.filepackage.service.impl;

import com.filepackage.dto.UserDto;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.entity.User;
import com.filepackage.repository.IUserRepository;
import com.filepackage.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AutoMapper autoMapper;

    private IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();

        return users.stream().map(user -> autoMapper.convertToDto(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

//    @Override
//    public List<UserDto> getAllUsers() {
//        return userRepository.findAll().stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDto addUser(UserDto userDto) {
//        User user = convertToEntity(userDto);
//        User savedUser = userRepository.save(user);
//        return convertToDto(savedUser);
//    }
//
//    @Override
//    public UserDto updateUser(Long id, UserDto userDto) {
//        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//        existingUser.setName(userDto.getName());
//        existingUser.setEmail(userDto.getEmail());
//        User updatedUser = userRepository.save(existingUser);
//        return convertToDto(updatedUser);
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    private UserDto convertToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        return userDto;
//    }
//
//    private User convertToEntity(UserDto userDto) {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        return user;
//    }
}
