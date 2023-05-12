package com.example.micronaut.entity.view;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record TeacherView(
        Long teachID,
        String teacher ) {

    @Override
    public String toString() {
        return "Teacher{" +
                "teachID=" + teachID +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}