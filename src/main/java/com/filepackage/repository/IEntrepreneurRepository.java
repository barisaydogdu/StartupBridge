package com.filepackage.repository;

import com.filepackage.entity.Entrepreneur;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntrepreneurRepository extends IBaseRepository<Entrepreneur> {
    // Özel sorgular gerekiyorsa burada tanımlanabilir
}
