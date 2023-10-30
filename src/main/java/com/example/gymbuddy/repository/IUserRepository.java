package com.example.gymbuddy.repository;

import com.example.gymbuddy.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserName(String userName);

    Long countByUserName(String userName);


}
