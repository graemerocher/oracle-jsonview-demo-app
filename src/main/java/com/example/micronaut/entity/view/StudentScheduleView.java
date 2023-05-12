package com.example.micronaut.entity.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record StudentScheduleView(
        @Nullable
        @GeneratedValue
        @Id
        Long id,
        @JsonProperty("class")
        StudentScheduleClassView clazz) {

        public StudentScheduleView(StudentScheduleClassView scheduleClassView) {
                this(null, scheduleClassView);
        }
        @Override
        public String toString() {
                return "StudentSchedule{" +
                        "id=" + id +
                        ", class=" + clazz +
                        '}';
        }
}