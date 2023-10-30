package com.example.gymbuddy.repository;

import com.example.gymbuddy.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntrenamientoRepository extends JpaRepository<Training, Long> {
}
