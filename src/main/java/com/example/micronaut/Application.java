package com.example.micronaut;

import java.time.LocalTime;

import com.example.micronaut.entity.Class;
import com.example.micronaut.entity.Student;
import com.example.micronaut.entity.StudentClass;
import com.example.micronaut.entity.Teacher;
import com.example.micronaut.repository.ClassRepository;
import com.example.micronaut.repository.StudentClassRepository;
import com.example.micronaut.repository.StudentRepository;
import com.example.micronaut.repository.TeacherRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

@Singleton
public class Application {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentClassRepository studentClassRepository;

    public Application(
            ClassRepository classRepository,
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            StudentClassRepository studentClassRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentClassRepository = studentClassRepository;
    }

    public static void main(String[] args) {
        Micronaut.run(args);
    }

    @EventListener
    public void init(StartupEvent startupEvent) {
        // Clear the existing tables
        classRepository.deleteAll();
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        studentClassRepository.deleteAll();

        // Use relational operations to insert three new rows in the STUDENT table
        Student dennis = studentRepository.save(new Student("Denis", 8.5));
        Student jill = studentRepository.save(new Student("Jill", 7.2));
        Student devjani = studentRepository.save(new Student("Devjani", 9.1));

        // Use relational operations to insert three new rows in the TEACHER table
        Teacher teacherOlya = teacherRepository.save(new Teacher("Ms. Olya"));
        Teacher teacherGraeme = teacherRepository.save(new Teacher("Mr. Graeme"));
        Teacher teacherYevhen = teacherRepository.save(new Teacher("Prof. Yevhen"));

        // Use relational operations to insert three new rows in the CLASS table
        Class math = classRepository.save(new Class("Math", "A101", LocalTime.of(10, 00), teacherGraeme));
        Class english = classRepository.save(new Class("English", "A102", LocalTime.of(11, 00), teacherYevhen));
        Class history = classRepository.save(new Class("History", "A103", LocalTime.of(12, 00), teacherOlya));

        // Use relational operations to inset six new rows into the STUDENT_CLASS table
        studentClassRepository.save(new StudentClass(dennis, math));
        studentClassRepository.save(new StudentClass(jill, math));
        studentClassRepository.save(new StudentClass(devjani, math));

        studentClassRepository.save(new StudentClass(dennis, history));
        studentClassRepository.save(new StudentClass(jill, english));
        studentClassRepository.save(new StudentClass(devjani, history));
    }
}
