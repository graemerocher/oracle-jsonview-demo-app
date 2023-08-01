
package com.example.micronaut.controller;

import java.util.List;
import java.util.Optional;

import com.example.micronaut.dto.CreateStudentViewDto;
import com.example.micronaut.entity.Course;
import com.example.micronaut.entity.Student;
import com.example.micronaut.entity.view.StudentScheduleCourseView;
import com.example.micronaut.entity.view.StudentScheduleView;
import com.example.micronaut.entity.view.StudentView;
import com.example.micronaut.repository.CourseRepository;
import com.example.micronaut.repository.StudentRepository;
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

@Controller("/students") // <1>
public final class StudentController {

    private final StudentViewRepository studentViewRepository;
    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    public StudentController(StudentViewRepository studentViewRepository, CourseRepository courseRepository, StudentRepository studentRepository) { // <2>
        this.studentViewRepository = studentViewRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Get("/{id}") // <3>
    public Optional<StudentView> findById(Long id) {
        return studentViewRepository.findById(id);
    }

    @Get("/") // <4>
    public Iterable<StudentView> findAll() {
        return studentViewRepository.findAll();
    }

    @Get("/student/{student}") // <5>
    public Optional<StudentView> findByStudent(@NonNull String student) {
        return studentViewRepository.findByStudent(student);
    }

    @Put("/{id}/average_grade/{averageGrade}") // <6>
    public Optional<StudentView> updateAverageGrade(Long id, @NonNull Double averageGrade) {
        return studentViewRepository.findById(id).flatMap(studentView -> {
            studentViewRepository.updateAverageGrade(id, averageGrade);
            return studentViewRepository.findById(id);
        });
    }

    @Put("/{id}/student/{student}") // <7>
    public Optional<StudentView> updateStudent(Long id, @NonNull String student) {
        return studentViewRepository.findById(id).flatMap(studentView -> {
            studentViewRepository.updateStudentByStudentId(id, student);
            return studentViewRepository.findById(id);
        });
    }

    @Post("/") // <8>
    @Status(HttpStatus.CREATED)
    public Optional<StudentView> create(@NonNull @Body CreateStudentViewDto createDto) {
        List<Course> courses = courseRepository.findByNameIn(createDto.courses());
        Student entity = this.studentRepository.save(new Student(
                null,
                createDto.student(),
                createDto.averageGrade(),
                courses
        ));
        List<StudentScheduleView> studentScheduleViews = courses.stream()
                .map(c -> new StudentScheduleView(new StudentScheduleCourseView(c))).toList();
        StudentView studentView = new StudentView(
                entity.id(),
                createDto.student(),
                createDto.averageGrade(),
                studentScheduleViews,
                null
        );
        return Optional.of(studentView);
    }

    @Delete("/{id}") // <9>
    @Status(HttpStatus.NO_CONTENT)
    void delete(Long id) {
        studentViewRepository.deleteById(id);
    }

    @Get("/max_average_grade") // <10>
    Optional<Double> findMaxAverageGrade() {
        return studentViewRepository.findMaxAverageGrade();
    }
}