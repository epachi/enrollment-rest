package com.apromore.enrollmentrest.controller;

import com.apromore.enrollmentrest.domain.Student;
import com.apromore.enrollmentrest.domain.StudentIdCourseCodeBody;
import com.apromore.enrollmentrest.exception.StudentEnrollmentException;
import com.apromore.enrollmentrest.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(summary = "Get all Students", description = "Returns all Students in the database")
    @ApiResponse(responseCode = "200", description = "Return all Students successfully")
    public ResponseEntity<List<Student>> getAllStudents() {
        log.info("getAllStudents() is invoked");
        List<Student> students = studentService.getAllStudents();
        log.info("All students retrieved. Number of students: {}", students.size());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Student by Id", description = "Returns a Student based on the provided Student Id")
    @ApiResponse(responseCode = "200", description = "Student matched input Student Id is found")
    @ApiResponse(responseCode = "404", description = "No Student matched input Student Id found")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        log.info("getStudentById() is invoked. Student Id: {}", id);
        Student student = studentService.getStudentById(id);
        log.info("Student with matching Student Id found");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new Student", description = "Add a new Student to the system")
    @ApiResponse(responseCode = "201", description = "Student created")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        log.info("addStudent() is invoked");
        Student addedStudent = studentService.addStudent(student);
        log.info("addStudent() is added. New Student Id: {}", addedStudent.getId());
        return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Student", description = "Update the detail of an existing Student")
    @ApiResponse(responseCode = "200", description = "Student updated successfully")
    @ApiResponse(responseCode = "404", description = "Student not found")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        log.info("updateStudent() is invoked. Student Id: {}", id);
        Student updated = studentService.updateStudent(id, updatedStudent);
        log.info("Student is updated. Student Id: {}", id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PostMapping("/enroll")
    @Operation(summary = "Enroll a Student to a Course", description = "Enroll a Student to a Course")
    @ApiResponse(responseCode = "200", description = "Enroll a Student to a Course successfully")
    @ApiResponse(responseCode = "404", description = "Student/Course not found")
    public ResponseEntity<Student> enrollStudent(@RequestBody @Valid StudentIdCourseCodeBody studentIdCourseCodeBody) {
        Long studentId = studentIdCourseCodeBody.getStudentId();
        String courseCode = studentIdCourseCodeBody.getCourseCode();
        log.info("enrollStudent() is invoked. Student Id: {}, Course code: {}", studentId, courseCode);
        Student enrolledStudent = studentService.enrollStudentToCourse(studentId, courseCode);
        if (enrolledStudent != null) {
            log.info("Student is enrolled successfully. Student Id: {}, Course code: {}", studentId, courseCode);
            return new ResponseEntity<>(enrolledStudent, HttpStatus.OK);
        }
        else {
            log.warn("Student cannot be enrolled. Student Id: {}, Course code: {}", studentId, courseCode);
            throw new StudentEnrollmentException("Student cannot be enrolled. Student Id: " + studentId +", Course code: "+ courseCode);
        }
    }

    @PostMapping("/unenroll")
    @Operation(summary = "Unenroll a Student to a Course", description = "Unnroll a Student to a Course")
    @ApiResponse(responseCode = "200", description = "Unenroll a Student to a Course successfully")
    @ApiResponse(responseCode = "404", description = "Student/Course not found")
    public ResponseEntity<Student> unenrollStudent(@RequestBody @Valid StudentIdCourseCodeBody studentIdCourseCodeBody) {
        Long studentId = studentIdCourseCodeBody.getStudentId();
        String courseCode = studentIdCourseCodeBody.getCourseCode();
        log.info("enrollStudent() is invoked. Student Id: {}, Course code: {}", studentId, courseCode);
        Student enrolledStudent = studentService.unenrollStudentToCourse(studentId, courseCode);
        if (enrolledStudent != null) {
            log.info("Student is unenrolled successfully. Student Id: {}, Course code: {}", studentId, courseCode);
            return new ResponseEntity<>(enrolledStudent, HttpStatus.OK);
        } else {
            log.warn("Student cannot be unenrolled. Student Id: {}, Course code: {}", studentId, courseCode);
            throw new StudentEnrollmentException("Student cannot be unenrolled. Student Id: " + studentId +", Course code: "+ courseCode);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Student by Id", description = "Delete an existing Student")
    @ApiResponse(responseCode = "204", description = "Student deleted successfully")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("deleteStudent() is invoked. Student Id: {}", id);
        studentService.deleteStudent(id);
        log.info("Student is deleted. Student Id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
