package com.example.gymbuddy.service.interfaces;

import com.example.gymbuddy.dto.UserTrainingDto;
import com.example.gymbuddy.model.UserTraining;

public interface IUserTrainingService {
    UserTrainingDto addUserToTraining(UserTraining userTraining) throws Exception;
}
