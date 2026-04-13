# 📚 PROYECTO: Estudiantes y Cursos

## 🎯 ¿De qué trata este proyecto?

Es un sistema sencillo donde:

- 👨‍🎓 Un estudiante se inscribe a un curso  
- 📘 Un curso tiene un profesor  
- 🔗 Cada estudiante pertenece a un curso  

👉 Ejemplo real:  
"Juan está inscrito en el curso de Matemáticas con el profesor Carlos"

---

## 🗄️ 1. BASE DE DATOS (SQL SERVER)

Se crea la base de datos:

```sql
CREATE DATABASE colegio;
USE colegio;

-- Tabla de cursos
CREATE TABLE cursos (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(50),
    profesor VARCHAR(50)
);

-- Tabla de estudiantes
CREATE TABLE estudiantes (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(50),
    edad INT,
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
🧠 Idea clave:

cursos → materias
estudiantes → personas
curso_id → conecta estudiante con curso 🔗
🌐 5. CONTROLLER (API REST)
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository repo;

    // 📄 Listar todos
    @GetMapping
    public List<Estudiante> listar() {
        return repo.findAll();
    }

    // 💾 Guardar
    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante e) {
        return repo.save(e);
    }

    // ✏️ Actualizar
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
            return null;
        }
    }

    // ❌ Eliminar
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



   


