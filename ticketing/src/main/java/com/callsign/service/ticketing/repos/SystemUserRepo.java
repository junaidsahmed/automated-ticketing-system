package com.callsign.service.ticketing.repos;

import com.callsign.service.ticketing.models.jwt.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Repository
public interface SystemUserRepo extends JpaRepository<SystemUser,Integer> {
    Optional<SystemUser> findByUserEmail(String email);
    boolean existsByUserEmail(String email);
}
