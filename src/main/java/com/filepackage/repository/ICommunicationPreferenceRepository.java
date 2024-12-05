package com.filepackage.repository;

import com.filepackage.entity.CommunicationPreference;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommunicationPreferenceRepository extends IBaseRepository<CommunicationPreference> {
    // Özel sorgular gerekiyorsa burada tanımlanabilir
}
