package com.example.micronaut.repository;

import com.example.micronaut.entity.Course;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface CourseRepository extends PageableRepository<Course, Long> {

    @Join("teacher")
    Course findByName(String name);

    @Join("teacher")
    List<Course> findByNameIn(List<String> names);
}
