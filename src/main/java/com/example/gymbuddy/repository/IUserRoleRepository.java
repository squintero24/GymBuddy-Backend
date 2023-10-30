package com.example.gymbuddy.repository;

import com.example.gymbuddy.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRoles, Long> {
}
