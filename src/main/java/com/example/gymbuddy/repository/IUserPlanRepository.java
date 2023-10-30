package com.example.gymbuddy.repository;

import com.example.gymbuddy.model.UserPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserPlanRepository extends JpaRepository<UserPlans, Long> {
}
