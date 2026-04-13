package com.ejemplo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.ejemplo.demo.model.Estudiante;
import com.ejemplo.demo.repository.EstudianteRepository;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository repo;

    @GetMapping
    public List<Estudiante> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante e) {
        return repo.save(e);
    }
}
