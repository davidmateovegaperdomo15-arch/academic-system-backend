package com.taller.student.infrastructure.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class StudentResourceTest {
  //Tarea: @ParameterizedTest crear test parametrizados y asi
  @Test
  void createListGetDelete_happyPath() {
    int createdId =
        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"Ana Lopez\",\"email\":\"ana@uni.edu\"}")
            .when()//10 escenarios distintos "not null' 'null' 'val' 'num' 'etc' @TestParametrized
            .post("/students")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("name", equalTo("Ana Lopez"))
            .extract()
            .path("id");

    given().when().get("/students").then().statusCode(200);

    given()
        .when()
        .get("/students/" + createdId)
        .then()
        .statusCode(200)
        .body("id", equalTo(createdId))
        .body("email", equalTo("ana@uni.edu"));

    given().when().delete("/students/" + createdId).then().statusCode(204);

    given().when().get("/students/" + createdId).then().statusCode(404).body("code", equalTo("NOT_FOUND"));
  }

  @Test
  void create_invalidNameFromDomain_returnsValidationError() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"name\":\"Bad123\",\"email\":\"valid@uni.edu\"}")
        .when()
        .post("/students")
        .then()
        .statusCode(400)
        .body("code", equalTo("VALIDATION_ERROR"));
  }

  @Test
  void create_invalidBody_returnsValidationError() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"name\":\"\",\"email\":\"not-an-email\"}")
        .when()
        .post("/students")
        .then()
        .statusCode(400)
        .body("code", equalTo("VALIDATION_ERROR"));
  }

  @Test
  void getById_notFound_returnsStructuredError() {
    given()
        .when()
        .get("/students/999999")
        .then()
        .statusCode(404)
        .body("code", equalTo("NOT_FOUND"))
        .body("status", equalTo(404));
  }
  //Tarea: Simplificar los parametros de cada test @BeforeEach etc
  @Test
  void delete_notFound_returns404() {
    given().when().delete("/students/999998").then().statusCode(404).body("code", equalTo("NOT_FOUND"));
  }
}
