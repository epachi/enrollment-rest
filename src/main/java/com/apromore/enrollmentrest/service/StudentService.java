package com.apromore.enrollmentrest.service;

import com.apromore.enrollmentrest.domain.Course;
import com.apromore.enrollmentrest.domain.Student;
import com.apromore.enrollmentrest.exception.ResourceNotFoundException;
import com.apromore.enrollmentrest.repository.CourseRepository;
import com.apromore.enrollmentrest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Student with Id: " + id + " not found"));
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = getStudentById(id);
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());
        return studentRepository.save(existingStudent);
    }

    public Student enrollStudentToCourse(Long studentId, String courseCode) {
        Student student = getStudentById(studentId);
        Course course = courseRepository.findByCode(courseCode);
        if (course == null) {
            throw new ResourceNotFoundException("Course with code: " + courseCode + " not found");
        }
        Set<Course> courses =  student.getCourses();
        if (!courses.contains(course)) {
            student.getCourses().add(course);
        }
        return studentRepository.save(student);
    }

    public Student unenrollStudentToCourse(Long studentId, String courseCode) {
        Student student = getStudentById(studentId);
        Course course = courseRepository.findByCode(courseCode);
        if (course == null) {
            throw new ResourceNotFoundException("Course with code: " + courseCode + " not found");
        }
        student.getCourses().remove(course);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        Set<Course> courses = student.getCourses();
        for (Course course : courses) {
            course.getStudents().remove(student);
            courseRepository.save(course);
        }
        student.getCourses().clear();
        studentRepository.save(student);
        studentRepository.delete(student);
    }
}
