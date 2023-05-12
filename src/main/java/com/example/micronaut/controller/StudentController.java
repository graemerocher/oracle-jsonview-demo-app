
package com.example.micronaut.controller;

import java.util.List;
import java.util.Optional;

import com.example.micronaut.dto.CreateStudentViewDto;
import com.example.micronaut.entity.view.StudentScheduleClassView;
import com.example.micronaut.entity.view.StudentScheduleView;
import com.example.micronaut.entity.view.StudentView;
import com.example.micronaut.repository.ClassRepository;
import com.example.micronaut.repository.view.StudentViewRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;

@Controller("/students")
public final class StudentController {

    private final StudentViewRepository studentViewRepository;
    private final ClassRepository classRepository;

    public StudentController(StudentViewRepository studentViewRepository, ClassRepository classRepository) {
        this.studentViewRepository = studentViewRepository;
        this.classRepository = classRepository;
    }

    @Get("/{id}")
    public Optional<StudentView> findById(Long id) {
        return studentViewRepository.findById(id);
    }

    @Get("/")
    public Iterable<StudentView> findAll() {
        return studentViewRepository.findAll();
    }

    @Get("/student/{student}")
    public Optional<StudentView> findByStudent(@NonNull String student) {
        return studentViewRepository.findByStudent(student);
    }

    @Put("/{id}/average_grade/{averageGrade}")
    public Optional<StudentView> updateAverageGrade(Long id, @NonNull Double averageGrade) {
        return studentViewRepository.findById(id).map(studentView -> {
            studentViewRepository.updateAverageGrade(id, averageGrade);
            return studentView;
        });
    }

    @Put("/{id}/student/{student}")
    public Optional<StudentView> updateStudent(Long id, @NonNull String student) {
        return studentViewRepository.findById(id).map(studentView -> {
            studentViewRepository.updateStudentByStudentId(id, student);
            return studentView;
        });
    }

    @Post("/")
    @Status(HttpStatus.CREATED)
    public Optional<StudentView> create(@NonNull @Body CreateStudentViewDto createDto) {
        List<StudentScheduleView> studentScheduleViews = classRepository.findByNameIn(createDto.classes()).stream()
                .map(c -> new StudentScheduleView(new StudentScheduleClassView(c))).toList();
        StudentView studentView = new StudentView(
                createDto.student(),
                createDto.averageGrade(),
                studentScheduleViews
        );
        studentView = studentViewRepository.save(studentView);
        return Optional.of(studentView);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    void delete(Long id) {
        studentViewRepository.deleteById(id);
    }

    @Get("/max_average_grade")
    Optional<Double> findMaxAverageGrade() {
        return studentViewRepository.findMaxAverageGrade();
    }
}
