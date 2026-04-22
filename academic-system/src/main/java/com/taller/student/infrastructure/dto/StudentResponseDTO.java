package com.taller.student.infrastructure.dto;

public class StudentResponseDTO {

    public Long id;
    public String name;
    public String email;
    public double averageGrade;
    public static class Builder {
        private final StudentResponseDTO dto = new StudentResponseDTO();

        public Builder id(Long id) { dto.id = id; return this; }
        public Builder name(String name) { dto.name = name; return this; }
        public Builder email(String email) { dto.email = email; return this; }
        public Builder averageGrade(Double averageGrade) { dto.averageGrade = averageGrade; return this; }

        public StudentResponseDTO build() { return dto; }
    }
}
