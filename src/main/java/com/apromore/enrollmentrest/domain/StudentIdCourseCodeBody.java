package com.apromore.enrollmentrest.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentIdCourseCodeBody {
    @NotNull
    private Long studentId;
    @NotNull
    private String courseCode;
}
