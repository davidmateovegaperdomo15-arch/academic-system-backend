package com.taller.shared.util;

public final class InputValidator {

  // Regex nivel PRO: Acepta letras, tildes, ñ y espacios. Longitud entre 2 y 60.
  private static final String NAME_REGEX = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]{2,60}$";
  // Regex estándar de la industria para correos.
  private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

  private InputValidator() {
    // Constructor privado para evitar que alguien haga un "new InputValidator()"
  }

  public static String sanitizeAndValidateName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("El nombre es obligatorio");
    }

    // Sanitizar: Quitamos espacios a los lados y reemplazamos múltiples espacios internos por uno solo
    String sanitized = name.trim().replaceAll("\\s+", " ");

    // Validar con Regex
    if (!sanitized.matches(NAME_REGEX)) {
      throw new IllegalArgumentException("El nombre tiene un formato inválido o caracteres no permitidos");
    }

    // Devolvemos el dato ya limpio (Normalizado)
    return sanitized;
  }

  public static String sanitizeAndValidateEmail(String email) {
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("El email es obligatorio");
    }

    // Sanitizar y Normalizar: Quitamos espacios y forzamos minúsculas
    String sanitized = email.trim().toLowerCase();

    if (sanitized.length() > 254) {
      throw new IllegalArgumentException("El email es demasiado largo");
    }

    if (!sanitized.matches(EMAIL_REGEX)) {
      throw new IllegalArgumentException("Formato de email inválido");
    }

    return sanitized;
  }
}