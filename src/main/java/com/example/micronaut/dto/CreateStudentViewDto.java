package com.example.micronaut.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record CreateStudentViewDto(
        String student,
        Double averageGrade,
        List<String> classes
) {}
