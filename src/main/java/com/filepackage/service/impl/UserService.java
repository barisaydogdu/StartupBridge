package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.UserDto;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.entity.User;
import com.filepackage.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements com.filepackage.service.interfaces.UserService {

    @Autowired
    AutoMapper autoMapper;

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();

        return users.stream().map(user -> autoMapper.convertToDto(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById (Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist with given id)"+userId));
        return autoMapper.convertToDto(user, UserDto.class);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
       //önce entitye sonra tekrardan dtoya çeviriyoruz burada
        User user = autoMapper.convertToEntity(userDto,User.class);
        User savedUser = userRepository.save(user);
        return  autoMapper.convertToDto(savedUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserDto updatedUser) {
        User user= userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User is not exist with given id:)" + id));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());
        //user.(updatedUser.getName());
        User updatedUserObj = userRepository.save(user);
        return autoMapper.convertToDto(updatedUser,UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User does not exist with given id"));
    userRepository.deleteById(userId);
    }
}
