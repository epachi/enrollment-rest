package com.apromore.enrollmentrest.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentTest {

  @Test
  public void testConstructor() {
    Long id = 1L;
    String name = "Andrew Thompson";
    Integer age = 20;
    Set<Course> courses = new HashSet<>();

    Student student = new Student(id, name, age, courses);

    assertNotNull(student);
    assertEquals(id, student.getId());
    assertEquals(name, student.getName());
    assertEquals(age, student.getAge());
    assertEquals(courses, student.getCourses());
  }

  @Test
  public void testGetId() {
    Long id = 1L;
    Student student = new Student();
    student.setId(id);

    assertEquals(id, student.getId());
  }

  @Test
  public void testSetName() {
    String name = "Andrew Thompson";
    Student student = new Student();
    student.setName(name);

    assertEquals(name, student.getName());
  }

  @Test
  public void testGetAge() {
    Integer age = 20;
    Student student = new Student();
    student.setAge(age);

    assertEquals(age, student.getAge());
  }

  @Test
  public void testGetCourses() {
    Set<Course> courses = new HashSet<>();
    Student student = new Student();
    student.setCourses(courses);

    assertEquals(courses, student.getCourses());
  }
}