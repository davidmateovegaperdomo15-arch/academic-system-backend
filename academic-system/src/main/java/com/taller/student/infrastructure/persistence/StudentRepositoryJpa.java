package com.taller.student.infrastructure.persistence;

import com.taller.student.domain.model.Student;
import com.taller.student.domain.repository.StudentRepository;
import com.taller.student.infrastructure.persistence.entity.StudentEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped // ¡Ahora ESTE es el repositorio oficial!
@Named("Jpa") // Le damos un nombre para poder inyectarlo específicamente si queremos
public class StudentRepositoryJpa implements StudentRepository {

    @Inject
    EntityManager em; // El motor de Hibernate que habla con PostgreSQL/MySQL

    @Override
    @Transactional // ¡Súper importante! Abre la "caja fuerte" para poder escribir en la BD
    public Student save(Student student) {
        // 1. Convertimos el Dominio (Student) a Entidad de Base de Datos (StudentEntity)
        StudentEntity entity = new StudentEntity(student.getId(), student.getName(), student.getEmail());

        if (entity.getId() == null) {
            em.persist(entity); // Hace el INSERT en SQL
        } else {
            entity = em.merge(entity); // Hace el UPDATE en SQL
        }

        // 2. Convertimos de vuelta a Dominio para que el Servicio siga feliz
        return new Student(entity.getId(), entity.getName(), entity.getEmail());
    }

    @Override
    public Optional<Student> findById(Long id) {
        // Busca en la tabla por Primary Key (Hace el SELECT)
        StudentEntity entity = em.find(StudentEntity.class, id);

        if (entity == null) {
            return Optional.empty();
        }
        return Optional.of(new Student(entity.getId(), entity.getName(), entity.getEmail()));
    }

    @Override
    public List<Student> findAll() {
        // Hacemos una consulta JPQL (Java Persistence Query Language)
        // Ojo: "SELECT s FROM StudentEntity s" consulta la CLASE, no la tabla. Hibernate lo traduce.
        List<StudentEntity> entities = em.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class).getResultList();

        return entities.stream()
                .map(e -> new Student(e.getId(), e.getName(), e.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional // Necesario para borrar datos
    public void deleteById(Long id) {
        StudentEntity entity = em.find(StudentEntity.class, id);
        if (entity != null) {
            em.remove(entity); // Hace el DELETE en SQL
        }
    }
}