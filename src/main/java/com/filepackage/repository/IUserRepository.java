package com.filepackage.repository;

import com.filepackage.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends IBaseRepository<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);

}