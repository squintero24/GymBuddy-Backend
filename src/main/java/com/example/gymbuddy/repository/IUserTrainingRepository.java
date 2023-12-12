package com.example.gymbuddy.repository;

import com.example.gymbuddy.model.Training;
import com.example.gymbuddy.model.UserTraining;
import com.example.gymbuddy.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserTrainingRepository extends JpaRepository <UserTraining,Long> {

   boolean existsByUserAndTraining(Users user, Training training);

}
