package com.apromore.enrollmentrest.controller;

import com.apromore.enrollmentrest.domain.Course;
import com.apromore.enrollmentrest.domain.Student;
import com.apromore.enrollmentrest.domain.StudentIdCourseCodeBody;
import com.apromore.enrollmentrest.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    MockMvc mockMvc;

    @Test
    void testGetAllStudentsViaEndPoint() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        List<Student> students = Arrays.asList(
                new Student(1L, "Andrew Thompson", 20, Collections.emptySet()),
                new Student(2L, "Peter Hanks", 21, Collections.emptySet()));

        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(students.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0]name", is(students.get(0).getName())))
                .andExpect(jsonPath("$[0]age", is(students.get(0).getAge())));;
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(
                new Student(1L, "Andrew Thompson", 20, Collections.emptySet()),
                new Student(2L, "Peter Hanks", 21, Collections.emptySet()));

        when(studentService.getAllStudents()).thenReturn(students);

        ResponseEntity<List<Student>> response = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
    }


    @Test
    void testGetStudentById() {
        Long id = 1L;
        Student student = new Student(id, "Andrew Thompson", 20, Collections.emptySet());

        when(studentService.getStudentById(id)).thenReturn(student);

        ResponseEntity<Student> response = studentController.getStudentById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void testGetStudentByIdViaEndpoint() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        Long id = 1L;
        String name = "Andrew Thompson";
        int age = 20;
        Student student = new Student(id, name, age, Collections.emptySet());
        when(studentService.getStudentById(id)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(student.getId().intValue())))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.age", is(age)));
    }

    @Test
    void testAddStudent() {
        Student student = new Student(2L, "Peter Hanks", 21, Collections.emptySet());

        when(studentService.addStudent(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.addStudent(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void testUpdateStudent() {
        Long id = 1L;
        Student updatedStudent = new Student(id, "Andrew Thompson", 20, Collections.emptySet());

        when(studentService.updateStudent(id, updatedStudent)).thenReturn(updatedStudent);

        ResponseEntity<Student> response = studentController.updateStudent(id, updatedStudent);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedStudent, response.getBody());
    }

    @Test
    void testEnrollStudent() {
        Long studentId = 1L;
        String courseCode = "JPL-101";
        Student enrolledStudent = new Student(studentId, "Andrew Thompson", 20,
                new HashSet<Course>(Arrays.asList(dummyCourse(courseCode))));

        when(studentService.enrollStudentToCourse(studentId, courseCode)).thenReturn(enrolledStudent);

        ResponseEntity<Student> response = studentController.enrollStudent(
                new StudentIdCourseCodeBody(studentId, courseCode));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enrolledStudent, response.getBody());
    }

    @Test
    void testUnenrollStudent() {
        Long studentId = 1L;
        String courseCode = "JPL-101";
        Student unenrolledStudent = new Student(studentId, "Andrew Thompson", 20, Collections.emptySet());

        when(studentService.unenrollStudentToCourse(studentId, courseCode)).thenReturn(unenrolledStudent);

        ResponseEntity<Student> response = studentController.unenrollStudent(
                new StudentIdCourseCodeBody(studentId, courseCode));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(studentService).unenrollStudentToCourse(studentId, courseCode);
        assertEquals(unenrolledStudent, response.getBody());
    }

    private Course dummyCourse(String courseCode) {
        return new Course(1000L, "Java Programming Language", "Introduction to Java Programming Language",
                courseCode, Collections.emptySet());
    }
}