package com.example.gymbuddy.service.impl;

import com.example.gymbuddy.dto.UserTrainingDto;
import com.example.gymbuddy.mapper.IEntrenamientoMapper;
import com.example.gymbuddy.model.Training;
import com.example.gymbuddy.model.UserTraining;
import com.example.gymbuddy.model.Users;
import com.example.gymbuddy.repository.IUserTrainingRepository;
import com.example.gymbuddy.service.interfaces.IUserTrainingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEntrenamientoServiceImpl implements IUserTrainingService {
    @Autowired
    private IUserTrainingRepository userTrainingRepository;
    @Override
    @Transactional
    public UserTrainingDto addUserToTraining(UserTraining userTraining) throws Exception{
//        if (userTraining == null || userTraining.getUser()==null || userTraining.getTraining()==null) {
//            throw new IllegalArgumentException("El parámetro 'userId' no puede ser nulo.");
//        }
        Users user = userTraining.getUser();
        Training training = userTraining.getTraining();
        if (userTrainingRepository.existsByUserAndTraining(user, training)) {
            throw new Exception("Usuario ya se encuentra en el entrenamiento");
        }



        userTrainingRepository.save(userTraining);

        return IEntrenamientoMapper.INSTANCE.toUserTrainingDto(userTraining);

    }
}
