package com.example.SpringbootIntern.repository;

import com.example.SpringbootIntern.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails, Long> {
    RegisterDetails findByEmail(String email);
}
