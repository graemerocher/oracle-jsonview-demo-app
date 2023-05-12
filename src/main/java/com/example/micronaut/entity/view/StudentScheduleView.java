package com.example.micronaut.entity.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record StudentScheduleView(
        Long id,
        @JsonProperty("class")
        StudentScheduleClassView clazz) {
        @Override
        public String toString() {
                return "StudentSchedule{" +
                        "id=" + id +
                        ", class=" + clazz +
                        '}';
        }
}