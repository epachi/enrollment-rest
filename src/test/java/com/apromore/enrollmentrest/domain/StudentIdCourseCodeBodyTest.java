package com.apromore.enrollmentrest.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentIdCourseCodeBodyTest {

    @Test
    public void testConstructor() {
        Long studentId = 1L;
        String courseCode = "JPL-101";

        StudentIdCourseCodeBody body = new StudentIdCourseCodeBody(studentId, courseCode);

        assertNotNull(body);
        assertEquals(studentId, body.getStudentId());
        assertEquals(courseCode, body.getCourseCode());
    }

    @Test
    public void testGetStudentId() {
        Long studentId = 1L;

        StudentIdCourseCodeBody body = new StudentIdCourseCodeBody();
        body.setStudentId(studentId);

        assertEquals(studentId, body.getStudentId());
    }

    @Test
    public void testGetCourseCode() {
        String courseCode = "JPL-101";

        StudentIdCourseCodeBody body = new StudentIdCourseCodeBody();
        body.setCourseCode(courseCode);

        assertEquals(courseCode, body.getCourseCode());
    }
}