package com.example.micronaut.repository;

import com.example.micronaut.entity.StudentCourse;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface StudentCourseRepository extends PageableRepository<StudentCourse, Long> {
}