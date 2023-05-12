package com.example.micronaut.domain;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record CreateStudentViewDto(
        String student,
        Double averageGrade,
        List<String> classes
) {}
