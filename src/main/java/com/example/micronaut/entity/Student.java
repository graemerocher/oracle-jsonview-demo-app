package com.example.micronaut.entity;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.jdbc.annotation.JoinTable;

import java.util.Collections;
import java.util.List;

@MappedEntity
public record Student(
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    @Nullable
    Long id,
    String name,
    Double averageGrade,
    @JoinTable(name = "STUDENT_CLASSES")
    @Relation(Relation.Kind.MANY_TO_MANY)
    List<Class> classes) {

    public Student(String name, Double averageGrade) {
        this(null, name, averageGrade, Collections.emptyList());
    }
}
