package com.taller.student.infrastructure.persistence;

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

  // ... getters y setters vacíos obligatorios para que Hibernate funcione ...
}