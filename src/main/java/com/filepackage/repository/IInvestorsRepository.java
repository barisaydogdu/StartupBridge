package com.filepackage.repository;

import com.filepackage.entity.Entrepreneur;
import com.filepackage.entity.Investors;
import com.filepackage.entity.Project;
import com.filepackage.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInvestorsRepository extends IBaseRepository<Investors>{
    Optional<Investors> findByUser(User user);
}
