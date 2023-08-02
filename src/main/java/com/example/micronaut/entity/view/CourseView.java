package com.example.micronaut.entity.view;

import com.example.micronaut.entity.Course;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalTime;

@Serdeable
public record CourseView(
        Long courseId,
        String name,
        TeacherView teacher,
        String room,
        LocalTime time) {


    public CourseView(Course course) {
        this(
                course.id(),
                course.name(),
                new TeacherView(
                        course.teacher().id(),
                        course.teacher().name()
                ),
                course.room(),
                course.time()
        );
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", teacher=" + teacher +
                ", room='" + room + '\'' +
                ", time=" + time +
                '}';
    }
}