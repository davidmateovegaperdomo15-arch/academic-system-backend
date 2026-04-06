package com.taller.student.infrastructure.mapper;

import com.taller.grade.domain.service.GradeCalculator;
import com.taller.student.domain.model.Student;
import com.taller.student.infrastructure.dto.StudentResponseDTO;

public class StudentMapper {

  // Hacemos el constructor privado para que nadie pueda instanciar esta clase con "new".
  // Los Mappers suelen ser clases utilitarias con métodos estáticos.
  private StudentMapper() {}

  /**
   * Convierte una entidad de dominio puro (Student) a un objeto de transferencia (DTO).
   * * ¿Por qué lo hacemos?
   * 1. Seguridad: Evitamos enviar datos sensibles de la entidad al frontend.
   * 2. Desacoplamiento: Si la Entidad cambia (ej. dividimos nombre en First y Last),
   * el frontend no se rompe porque este Mapper lo adapta al DTO original.
   * 3. Limpieza: Quitamos este código aburrido de "seteo" de la capa de lógica de negocio.
   */
  public static StudentResponseDTO toResponseDTO(Student student, GradeCalculator calculator) {
    if (student == null) {
      return null;
    }

    StudentResponseDTO dto = new StudentResponseDTO();

    // Mapeo directo 1 a 1
    dto.id = student.getId();
    dto.name = student.getName();
    dto.email = student.getEmail();

    // Mapeo calculado: Le pedimos a la entidad que use la calculadora
    // y guardamos el resultado final en el DTO para el cliente.
    dto.averageGrade = student.calculateFinalGrade(calculator);

    return dto;
  }
}