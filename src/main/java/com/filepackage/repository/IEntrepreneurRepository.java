package com.filepackage.repository;

import com.filepackage.entity.Entrepreneur;
import com.filepackage.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEntrepreneurRepository extends IBaseRepository<Entrepreneur> {
    Optional<Entrepreneur> findByUser(User user);
}
