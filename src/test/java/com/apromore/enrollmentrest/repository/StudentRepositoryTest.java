package com.apromore.enrollmentrest.repository;

import com.apromore.enrollmentrest.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

  @Autowired
  private StudentRepository studentRepository;

  @Test
  public void findById_whenStudentExists_returnsStudent() {
    Long id = 1L;
    Student expected = dummyStudent();
    studentRepository.save(expected);

    Optional<Student> actual = studentRepository.findById(id);

    assertThat(actual).isPresent().contains(expected);
  }

  @Test
  public void save_savesStudent() {
    Student student = dummyStudent();

    studentRepository.save(student);

    assertThat(student.getId()).isNotNull();
  }

  @Test
  public void deleteById_deletesStudent() {
    Long id = 1L;
    studentRepository.save(dummyStudent());

    studentRepository.deleteById(id);

    assertThat(studentRepository.existsById(id)).isFalse();
  }

  private Student dummyStudent() {
    Student student = new Student();
    student.setName("Andrew Thomson");
    student.setAge(20);
    return student;
  }
}