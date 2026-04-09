package com.taller.student.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class GradeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private double value;

  // Relación N:1 -> Muchas notas pertenecen a un estudiante
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", nullable = false)
  private StudentEntity student;

  // Constructores
  public GradeEntity() {}

  public GradeEntity(double value) {
    this.value = value;
  }

  // Getters y Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public double getValue() { return value; }
  public void setValue(double value) { this.value = value; }
  public StudentEntity getStudent() { return student; }
  public void setStudent(StudentEntity student) { this.student = student; }
}