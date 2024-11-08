package com.filepackage.repository;

import com.filepackage.entity.CommunicationPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationPreferencesRepository extends JpaRepository<CommunicationPreferences, Long> {
    
}
