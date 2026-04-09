package com.taller.grade.infrastructure.persistence;

import com.taller.student.infrastructure.persistence.StudentEntity;

@Entity
@Table(name = "grades")
public class GradeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private double value;

  // Muchas Notas -> Pertenecen a 1 Estudiante
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", nullable = false) // Esta es la columna real en PostgreSQL (Clave Foránea)
  private StudentEntity student;

  // ... getters y setters ...
}
