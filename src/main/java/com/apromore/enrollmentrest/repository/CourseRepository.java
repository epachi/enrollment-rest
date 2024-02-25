package com.apromore.enrollmentrest.repository;

import com.apromore.enrollmentrest.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCode(String code);
}
