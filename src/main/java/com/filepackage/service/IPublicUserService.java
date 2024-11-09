package com.filepackage.service;

import com.filepackage.dto.PublicUsersDto;

public interface IPublicUserService <PublicUsersDto,Long> extends IBaseService<com.filepackage.dto.PublicUsersDto,Long>{
    PublicUsersDto createPublicUser(PublicUsersDto publicUsersDto);
}
