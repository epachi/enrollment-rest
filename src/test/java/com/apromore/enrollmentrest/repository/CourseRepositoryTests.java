package com.apromore.enrollmentrest.repository;

import com.apromore.enrollmentrest.domain.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findById_whenCourseExists_returnsCourse() {
        Long id = 1L;
        Course expected = dummyCourseToBeSaved();
        courseRepository.save(expected);

        Optional<Course> actual = courseRepository.findById(id);

        assertThat(actual).isPresent().contains(expected);
    }

    @Test
    public void findByCode_whenCourseExists_returnsCourse() {
        String code = "CS101";
        Course expected = dummyCourseToBeSaved();
        expected.setCode(code);
        courseRepository.save(expected);

        Course actual = courseRepository.findByCode(code);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void save_savesCourse() {
        Course course = dummyCourseToBeSaved();

        courseRepository.save(course);

        assertThat(course.getId()).isNotNull();
    }

    @Test
    public void deleteById_deletesCourse() {
        Long id = 1L;
        courseRepository.save(dummyCourseToBeSaved());

        courseRepository.deleteById(id);

        assertThat(courseRepository.existsById(id)).isFalse();
    }

    private Course dummyCourseToBeSaved() {
        Course course = new Course();
        course.setName("Java Programming Language");
        course.setDescription("Introduction to Java Programming Language");
        course.setCode("JPL-101");
        course.setStudents(Collections.emptySet());
        return course;
    }
}