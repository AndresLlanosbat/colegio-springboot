package com.ejemplo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.demo.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
