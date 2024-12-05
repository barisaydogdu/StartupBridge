package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.PublicUsersDto;
import com.filepackage.dto.UserDto;
import com.filepackage.entity.PublicUsers;
import com.filepackage.entity.User;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IPublicUsersRepository;
import com.filepackage.service.IPublicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicUserService implements IPublicUserService<PublicUsersDto,Long> {
    @Autowired
    AutoMapper autoMapper;
    IPublicUsersRepository publicUsersRepository;

    @Autowired
    public PublicUserService(IPublicUsersRepository publicUsersRepository){
        this.publicUsersRepository=publicUsersRepository;
    }

    @Override
    public List<PublicUsersDto> getAll() {
        List<PublicUsers> users=publicUsersRepository.findAll();

        return users.stream().map(publicUser -> autoMapper.convertToDto(publicUser,PublicUsersDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId) {
        PublicUsers user = publicUsersRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User does not exist with given id"));
        publicUsersRepository.deleteById(userId);
    }

    @Override
    public PublicUsersDto update(Long userId, PublicUsersDto updatedUser) {
        PublicUsers publicUsers= publicUsersRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User is not exist with given id:)" + userId));
        publicUsers.setUsername(updatedUser.getUsername());
        publicUsers.setEmail(updatedUser.getEmail());
        publicUsers.setPassword(updatedUser.getPassword());
        publicUsers.setRole(updatedUser.getRole());
        //user.(updatedUser.getName());
        PublicUsers updatedUserObj = publicUsersRepository.save(publicUsers);
        return autoMapper.convertToDto(updatedUser,PublicUsersDto.class);
    }

    @Override
    public PublicUsersDto getById (Long publicUserId) {
        PublicUsers publicUsers = publicUsersRepository.findById(publicUserId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist with given id)"+publicUserId));
        return autoMapper.convertToDto(publicUsers, PublicUsersDto.class);
    }


    @Override
    public PublicUsersDto createPublicUser(PublicUsersDto publicUsersDto) {
        //önce entitye sonra tekrardan dtoya çeviriyoruz burada
        PublicUsers user = autoMapper.convertToEntity(publicUsersDto,PublicUsers.class);
        PublicUsers savedUser = publicUsersRepository.save(user);
        return  autoMapper.convertToDto(savedUser,PublicUsersDto.class);
    }
}
