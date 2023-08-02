package com.example.micronaut.entity.view;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record TeacherView(
        Long teacherId,
        String teacher ) {

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}