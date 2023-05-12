package com.example.micronaut.entity.view;

import com.example.micronaut.entity.Class;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalTime;

@Serdeable
public record StudentScheduleClassView(
        Long classID,
        String name,
        TeacherView teacher,
        String room,
        LocalTime time) {


    public StudentScheduleClassView(Class clazz) {
        this(
                clazz.id(),
                clazz.name(),
                new TeacherView(
                        clazz.teacher().id(),
                        clazz.teacher().name()
                ),
                clazz.room(),
                clazz.time()
        );
    }

    @Override
    public String toString() {
        return "StudentScheduleClass{" +
                "classID=" + classID +
                ", teacher=" + teacher +
                ", room='" + room + '\'' +
                ", time=" + time +
                '}';
    }
}