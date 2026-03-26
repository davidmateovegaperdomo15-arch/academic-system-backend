package com.taller.student.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private Map<Long, Student> database = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            student = new Student(currentId++, student.getName(), student.getEmail());
        }
        database.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }
}