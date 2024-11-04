package com.filepackage.repository;

import com.filepackage.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IBaseRepository<User> {
}