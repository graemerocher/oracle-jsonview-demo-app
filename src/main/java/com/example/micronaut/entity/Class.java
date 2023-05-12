package com.example.micronaut.entity;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@MappedEntity
public record Class(
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    @Nullable
    Long id,
    @NotNull
    String name,
    @NotNull
    String room,
    @NotNull
    LocalTime time,
    @Nullable
    @Relation(Relation.Kind.MANY_TO_ONE)
    Teacher teacher) {

    public Class(String name, String room, LocalTime time, @Nullable Teacher teacher) {
        this(null, name, room, time, teacher);
    }
}