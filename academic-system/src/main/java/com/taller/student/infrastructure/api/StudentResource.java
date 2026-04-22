package com.taller.student.infrastructure.api;

import com.taller.student.application.CreateStudentUseCase;
import com.taller.student.application.StudentService;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
import jakarta.validation.Valid;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Estudiantes", description = "Operaciones relacionadas con los estudiantes de la universidad") // <-- Título en Swagger
public class StudentResource {
/*
    private final Function<StudentDTO, StudentResponseDTO> createStudentUseCase;

    public StudentResource(Function<StudentDTO, StudentResponseDTO> createStudentUseCase) {
        this.createStudentUseCase = createStudentUseCase;
    }

    @POST
    public Response create(
    @Parameter(description = "Datos del estudiante a registrar", required = true)
    @Valid StudentDTO dto) {
        // En lugar de llamar a service.createStudent(dto), simplemente "aplicas" la función
        StudentResponseDTO responseDTO = createStudentUseCase.apply(dto);
        return Response.status(201).entity(responseDTO).build();
    }*/
    //GlobalExceptionHandler o RestAdvice o ControllerAdvice
    private final CreateStudentUseCase createStudentUseCase;
    private final StudentService service;
    public StudentResource(CreateStudentUseCase createStudentUseCase, StudentService service) {
      this.createStudentUseCase = createStudentUseCase;
      this.service = service;
    }
    @POST
    @Operation(summary = "Crear un nuevo estudiante", description = "Recibe un DTO y guarda al estudiante en la base de datos.")
    @APIResponse(responseCode = "201", description = "Estudiante creado exitosamente")
    @APIResponse(responseCode = "400", description = "Datos inválidos en el JSON")
    public Response create(@Parameter(description = "Datos del estudiante a registrar", required = true)
                               @Valid StudentDTO dto ) {
        //TAREA: @Bodyrequest en quarkus o parecidos. @POST recibe parametros tambien pero en spring, ver si se puede hacer algo parecido aca
        //SWAGGER investigar a fondo Fast APi like
        StudentResponseDTO responseDTO = createStudentUseCase.run(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }
    // 2. GET /students
    @GET
    @Operation(summary = "Listar todos los estudiantes", description = "Retorna una lista completa de todos los estudiantes registrados en el sistema.")
    @APIResponse(responseCode = "200", description = "Lista de estudiantes obtenida correctamente")
    public Response getAll() {
        List<StudentResponseDTO> students = service.getAllStudents();
        // Retornamos 200 (OK)
        return Response.ok(students).build();
    }
    // 3. GET /students/{id}
    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar estudiante por ID", description = "Retorna los detalles de un estudiante específico usando su ID numérico.")
    @APIResponse(responseCode = "200", description = "Estudiante encontrado exitosamente")
    @APIResponse(responseCode = "404", description = "El estudiante no existe en la base de datos")
    public Response getById(
      @Parameter(description = "El ID único del estudiante", required = true, example = "1")
      @PathParam("id") Long id) {
        StudentResponseDTO student = service.getStudentById(id);
        // Si no lanza excepción, retornamos 200 (OK)
        return Response.ok(student).build();
    }
    // 4. DELETE /students/{id}
    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar estudiante", description = "Borra físicamente a un estudiante de la base de datos de la universidad.")
    @APIResponse(responseCode = "204", description = "Estudiante eliminado correctamente (Sin contenido para retornar)")
    @APIResponse(responseCode = "404", description = "El estudiante a eliminar no fue encontrado")
    public Response delete(
        @Parameter(description = "El ID único del estudiante a eliminar", required = true, example = "1")
        @PathParam("id") Long id) {
        service.deleteStudentById(id);
        // Retornamos 204 (No Content) porque la operación fue exitosa pero no hay nada que devolver
        return Response.noContent().build();
    }

}