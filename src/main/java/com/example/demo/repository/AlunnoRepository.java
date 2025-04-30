package com.example.demo.repository;

import com.example.demo.entity.Alunno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunnoRepository extends JpaRepository<Alunno, Long> {
    /*public Optional<Alunno> findById(Long id);*/
}
