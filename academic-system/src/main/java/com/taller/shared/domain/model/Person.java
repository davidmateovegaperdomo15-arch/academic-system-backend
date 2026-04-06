package com.taller.shared.domain.model;
import com.taller.shared.util.InputValidator;
public abstract class Person {
  protected String name;
  protected String email;

  protected Person(String name, String email) {
    this.name = InputValidator.sanitizeAndValidateName(name);
    this.email = InputValidator.sanitizeAndValidateEmail(email);
  }
  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void updateName(String name) {
    this.name = InputValidator.sanitizeAndValidateName(name);
  }
  public void updateEmail(String email) {
    this.email = InputValidator.sanitizeAndValidateEmail(email);
  }
}