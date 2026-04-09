package com.taller.student.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 1. Le dice a Hibernate: "Esta clase es una tabla de base de datos"
@Table(name = "students") // 2. Le damos el nombre exacto de la tabla
public class StudentEntity {

  @Id // 3. Esto es la Clave Primaria (Primary Key)
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. "Base de datos, encárgate del Autoincremento (1, 2, 3...)"
  private Long id;

  @Column(nullable = false, length = 60) // No puede ser nulo en BD y máximo 60 caracteres
  private String name;


  @Column(nullable = false, unique = true) // El email no se puede repetir en la tabla
  private String email;

  // 5. LA RELACIÓN (1 Estudiante -> Muchas Notas)
  @OneToMany(mappedBy = "student", //GradeEntity deberia tener el id?
    cascade = CascadeType.ALL, //Save automatico de lo que entre
    fetch = FetchType.LAZY, //EAGER trae todo de una vez, lazy solo trae al estudiante
    orphanRemoval = true)//Mata huerfanos
  private List<GradeEntity> grades = new ArrayList<>();

  // Constructores (Hibernate necesita un constructor vacío)
  public StudentEntity() {}

  public StudentEntity(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  // Getters y Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public List<GradeEntity> getGrades() { return grades; }
  public void setGrades(List<GradeEntity> grades) { this.grades = grades; }

  // Método de conveniencia para mantener la relación bidireccional sincronizada
  public void addGrade(GradeEntity grade) {
    grades.add(grade);
    grade.setStudent(this);
  }
}