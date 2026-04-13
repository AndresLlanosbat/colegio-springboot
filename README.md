🏫 📚 PROYECTO: Estudiantes y Cursos

👉 Vida real:
Un estudiante se inscribe a un curso
Relación clara y lógica
🗄️ 1. BASE DE DATOS (SQL SERVER)
CREATE DATABASE colegio;
GO

USE colegio;
GO

-- TABLA CURSOS
CREATE TABLE cursos (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(50),
    profesor VARCHAR(50)
);

-- TABLA ESTUDIANTES
CREATE TABLE estudiantes (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(50),
    edad INT,
    curso_id INT,

    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
⚙️ 2. application.properties (SQL SERVER)
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=colegio
spring.datasource.username=sa
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
🧩 3. ENTIDADES (MODEL)
📘 Curso.java
package com.ejemplo.demo.model;

import jakarta.persistence.*;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String profesor;

    // getters y setters
}
👨‍🎓 Estudiante.java
package com.ejemplo.demo.model;

import jakarta.persistence.*;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // getters y setters
}
🗂️ 4. REPOSITORY
CursoRepository
package com.ejemplo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.demo.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
EstudianteRepository
package com.ejemplo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.demo.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
🎮 5. CONTROLLER (API REST)
EstudianteController
package com.ejemplo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.ejemplo.demo.model.Estudiante;
import com.ejemplo.demo.repository.EstudianteRepository;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository repo;

    // 🔍 GET - Listar todos
    @GetMapping
    public List<Estudiante> listar() {
        return repo.findAll();
    }

    // 💾 POST - Guardar
    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante e) {
        return repo.save(e);
    }

    // ✏️ PUT - Actualizar
    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante e) {
        Optional<Estudiante> estudiante = repo.findById(id);

        if (estudiante.isPresent()) {
            Estudiante est = estudiante.get();
            est.setNombre(e.getNombre());
            est.setEdad(e.getEdad());
            est.setCurso(e.getCurso());
            return repo.save(est);
        } else {
            return null; // también puedes manejar error mejor
        }
    }

    // ❌ DELETE - Eliminar
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Estudiante eliminado correctamente";
        } else {
            return "Estudiante no encontrado";
        }
    }
}
🧠 🏫 ¿QUÉ ES ESTE PROYECTO?

👉 Es un sistema sencillo donde:

👨‍🎓 Estudiantes → personas
📘 Cursos → materias
🔗 Cada estudiante pertenece a un curso

💡 En palabras simples:

“Un estudiante está inscrito en un curso”
🧱 🗄️ 1. BASE DE DATOS (SQL SERVER)

Tenemos 2 tablas:

📘 Tabla: cursos

Guarda las materias
id	nombre	profesor
1	Matemáticas	Carlos Pérez
👨‍🎓 Tabla: estudiantes

Guarda los estudiantes

id	nombre	edad	curso_id
1	Juan	15	1

👉 curso_id conecta con cursos
👉 Esto es una relación (FOREIGN KEY) 🔗
⚙️ 🔌 2. CONEXIÓN (application.properties)

Aquí le dices al programa:
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=colegio
spring.datasource.username=sa
spring.datasource.password=123456
💡 Traducción humana:

“Conéctate a esta base de datos con este usuario”
🧩 🧱 3. MODELOS (LAS CLASES)
📘 Curso.java

👉 Representa la tabla cursos
@Entity
public class Curso {
💡 Significa:

“Esto es una tabla en la base de datos”
👨‍🎓 Estudiante.java
@ManyToOne
@JoinColumn(name = "curso_id")
private Curso curso;
💡 Traducción magistral:

“Muchos estudiantes pueden estar en un solo curso”
🗂️ 📦 4. REPOSITORY
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>
💡 Esto es magia de Spring Boot 🧙‍♂️

👉 Te permite sin programar:

Guardar
Buscar
Eliminar
Actualizar
🔄 🔁 ¿CÓMO FUNCIONA TODO JUNTO?
🧠 Flujo completo (IMPORTANTE)
Tú envías datos desde Postman 🧪
El Controller recibe la petición 📩
El Repository guarda o consulta 📦
La Base de datos almacena 🗄️
Te devuelve respuesta ✅
