package com.example.demo.repository;



import com.example.demo.entity.Docente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    @EntityGraph(attributePaths = "corsi")
    List<Docente> findAll();

}
