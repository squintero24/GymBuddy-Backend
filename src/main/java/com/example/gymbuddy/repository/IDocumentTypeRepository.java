package com.example.gymbuddy.repository;

import com.example.gymbuddy.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentTypeRepository extends JpaRepository<DocumentType,Long> {
}
