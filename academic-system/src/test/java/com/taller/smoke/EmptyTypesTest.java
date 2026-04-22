package com.taller.smoke;

import com.taller.common.base.BaseEntity;
import com.taller.common.constants.AppConstants;
import com.taller.grade.dto.GradeDTO;
import com.taller.shared.api.ApiErrorResponse;
import com.taller.shared.security.security;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyTypesTest {

  @Test
  void instantiablePlaceholders() {
    assertNotNull(new BaseEntity());
    assertNotNull(new AppConstants());
    assertNotNull(new security());
    GradeDTO g = new GradeDTO();
    g.value = 3.5;
    assertEquals(3.5, g.value);
    assertNotNull(new ApiErrorResponse());
  }
}
