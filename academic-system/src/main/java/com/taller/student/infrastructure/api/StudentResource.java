package com.taller.student.infrastructure.api;

import com.taller.student.application.StudentService;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Estudiantes", description = "Operaciones relacionadas con los estudiantes de la universidad") // <-- Título en Swagger
public class StudentResource {


    private final StudentService service;
    public StudentResource(StudentService service) {
        this.service = service;
    }

    @POST
    @Operation(summary = "Crear un nuevo estudiante", description = "Recibe un DTO y guarda al estudiante en la base de datos.")
    @APIResponse(responseCode = "201", description = "Estudiante creado exitosamente")
    @APIResponse(responseCode = "400", description = "Datos inválidos en el JSON")
    public Response create(StudentDTO dto) {
        //TAREA: @Bodyrequest en quarkus o parecidos. @POST recibe parametros tambien pero en spring, ver si se puede hacer algo parecido aca
        //SWAGGER investigar a fondo Fast APi like
        StudentResponseDTO responseDTO = service.createStudent(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }
    // 2. GET /students
    @GET
    public Response getAll() {
        List<StudentResponseDTO> students = service.getAllStudents();
        // Retornamos 200 (OK)
        return Response.ok(students).build();
    }
    // 3. GET /students/{id}
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        StudentResponseDTO student = service.getStudentById(id);
        // Si no lanza excepción, retornamos 200 (OK)
        return Response.ok(student).build();
    }
    // 4. DELETE /students/{id}
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.deleteStudentById(id);
        // Retornamos 204 (No Content) porque la operación fue exitosa pero no hay nada que devolver
        return Response.noContent().build();
    }

}