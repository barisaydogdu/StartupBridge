package com.filepackage.repository;

import com.filepackage.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends IBaseRepository<User> {
}