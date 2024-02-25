package com.apromore.enrollmentrest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

//import java.util.*;

import com.apromore.enrollmentrest.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.apromore.enrollmentrest.domain.Student;
import com.apromore.enrollmentrest.repository.CourseRepository;
import com.apromore.enrollmentrest.repository.StudentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testGetAllStudents() {
        List<Student> students = Arrays.asList(dummyStudent(1L));
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> result = studentService.getAllStudents();
        assertEquals(students, result);
    }

    @Test
    public void testGetStudentById() {
        Long id = 1L;
        Student student = dummyStudent(id);
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(id);

        assertEquals(student, result);
    }

    @Test
    public void testAddStudent() {
        Student student = dummyStudent(1L);
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.addStudent(student);

        assertEquals(student, result);
    }

    @Test
    public void testUpdateStudent() {
        Long id = 1L;
        Student existingStudent = dummyStudent(id);
        Student updatedStudent = dummyStudent(id);
        updatedStudent.setName("updatedName");
        when(studentRepository.findById(id)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);
        Student result = studentService.updateStudent(id, updatedStudent);

        assertEquals(existingStudent, result);
    }

    @Test
    public void testEnrollStudentToCourse_CourseNotFound() {
        Long studentId = 1L;
        String invalidCourseCode = "INVALID";
        Student student = dummyStudent(studentId);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findByCode(invalidCourseCode)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.enrollStudentToCourse(studentId, invalidCourseCode);
        });
    }

    private Student dummyStudent(long id) {
        return new Student(id, "Andrew Thompson", 20, Collections.emptySet());
    }
}