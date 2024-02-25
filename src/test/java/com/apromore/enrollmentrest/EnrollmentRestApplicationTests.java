package com.apromore.enrollmentrest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnrollmentRestApplicationTests {
  @Test
  void testRun() {
    SpringApplication app = new SpringApplication(EnrollmentRestApplication.class);
    app.run();
    assertNotNull(app);
  }
}