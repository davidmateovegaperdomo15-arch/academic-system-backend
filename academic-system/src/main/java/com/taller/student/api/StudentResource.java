package com.taller.student.api;

import com.taller.grade.dto.GradeDTO;
import com.taller.student.application.StudentService;
import com.taller.student.dto.StudentDTO;
import com.taller.student.dto.StudentResponseDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentService service;
    public StudentResource(StudentService service) {
        this.service = service;
    }

    @POST
    public StudentResponseDTO create(StudentDTO dto) {
        return service.createStudent(dto);
    }

}