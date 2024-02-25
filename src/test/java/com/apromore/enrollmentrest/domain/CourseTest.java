package com.apromore.enrollmentrest.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseTest {

    private Course course;

    @BeforeEach
    public void setup() {
        course = new Course();
    }

    @Test
    public void testConstructor() {
        Course course = new Course(1000L, "Java Programming Language",
                "Introduction to Java Programming Language",
                "JPL-101", Collections.emptySet());
        assertNotNull(course);
        assertEquals("JPL-101", course.getCode());
    }

    @Test
    public void testGetId() {
        Long id = 1L;
        course.setId(id);
        assertEquals(id, course.getId());
    }

    @Test
    public void testGetName() {
        String name = "Java Programming Language";
        course.setName(name);
        assertEquals(name, course.getName());
    }

    @Test
    public void testGetDescription() {
        String description = "Introduction to Java Programming Language";
        course.setDescription(description);
        assertEquals(description, course.getDescription());
    }

    @Test
    public void testGetCode() {
        String code = "JPL-101";
        course.setCode(code);
        assertEquals(code, course.getCode());
    }

    @Test
    public void testGetStudents() {
        Set<Student> students = new HashSet<>();
        course.setStudents(students);
        assertEquals(students, course.getStudents());
    }

    @Test
    public void testEquals() {
        Course c1 = new Course(2000L, "CS101", "Spring Framework", "Introductory CS course", Collections.emptySet());
        Course c2 = new Course(2000L, "CS101", "Spring Framework", "Introductory CS course", Collections.emptySet());

        assertTrue(c1.equals(c2));
    }

}