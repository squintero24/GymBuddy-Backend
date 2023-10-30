package com.example.gymbuddy.repository;

import com.example.gymbuddy.model.Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanRepository extends JpaRepository<Plans, Long> {
}
