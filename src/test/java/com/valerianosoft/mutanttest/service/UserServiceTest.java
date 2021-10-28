package com.valerianosoft.mutanttest.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceTest {

  private UserService service;

  @BeforeEach
  void setUp() {
    this.service = new UserService();
  }

  @Test
  @DisplayName("shoud create")
  void shouldCreate() {
    final var name = "thalles";
    final var age = 24;
    final var retrieved = this.service.create(name, age);

    assertNotNull(retrieved);
    assertEquals(name, retrieved.getName());
  }

}