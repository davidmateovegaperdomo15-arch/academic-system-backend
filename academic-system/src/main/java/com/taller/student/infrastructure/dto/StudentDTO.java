package com.taller.student.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class StudentDTO {
    //Tarea Pruebas de integracion Y unitarias entender y aplicar:

    //Tarea Inv Y apl pruebas uni y de int al createStudentUsercase
    // NotBlank valida dos cosas a la vez: que no sea null, y que no sea un string vacío ("")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 60, message = "El nombre debe tener entre 2 y 60 caracteres")
    public String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    public String email;
}