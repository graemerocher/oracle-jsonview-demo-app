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
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.testcontainers.containers.OracleContainer;
//import org.testcontainers.utility.DockerImageName;

@Singleton
public class Application {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final StudentClassRepository studentClassRepository;
    private final TeacherRepository teacherRepository;

    public Application(
            StudentRepository studentRepository,
            ClassRepository classRepository,
            StudentClassRepository studentClassRepository,
            TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.studentClassRepository = studentClassRepository;
        this.teacherRepository = teacherRepository;
    }

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Micronaut.run(args);
//        try(OracleContainer oracleContainer = new OracleContainer(DockerImageName.parse("gvenzl/oracle-free:latest-faststart")
//                .asCompatibleSubstituteFor("gvenzl/oracle-xe"))
//                .withDatabaseName("test")) {
//            oracleContainer.start();
//            initApp(oracleContainer);
//        } catch (Exception e) {
//            LOG.error("An error occurred", e);
//            System.exit(1);
//        }
    }

//    /**
//     * Initializes application context using datasource parameters from the underlying test container.
//     *
//     * @param oracleContainer the Oracle test container started before app init
//     * @return the application context
//     */
//    private static ApplicationContext initApp(OracleContainer oracleContainer) {
//        String username = oracleContainer.getUsername();
//        String password = oracleContainer.getPassword();
//        String url = oracleContainer.getJdbcUrl();
//        return Micronaut.run(Application.class, "-jdbc-url=" + url,
//                "-jdbc-username=" + username, "-jdbc-password=" + password);
//    }

    @EventListener
    public void init(StartupEvent startupEvent) {
        studentClassRepository.deleteAll();
        classRepository.deleteAll();
        teacherRepository.deleteAll();
        studentRepository.deleteAll();

        Teacher teacherAnna = teacherRepository.save(new Teacher("Mrs. Anna"));
        Teacher teacherJeff = teacherRepository.save(new Teacher("Mr. Jeff"));

        Student denis = studentRepository.save(new Student("Denis", 8.5));
        Student josh = studentRepository.save(new Student("Josh", 7.2));
        Student fred = studentRepository.save(new Student("Fred", 9.1));

        Class math = classRepository.save(new Class("Math", "A101", LocalTime.of(10, 00), teacherAnna));
        Class english = classRepository.save(new Class("English", "A102", LocalTime.of(11, 00), teacherJeff));
        Class german = classRepository.save(new Class("German", "A103", LocalTime.of(12, 00), teacherAnna));

        studentClassRepository.save(new StudentClass(denis, math));
        studentClassRepository.save(new StudentClass(josh, math));
        studentClassRepository.save(new StudentClass(fred, math));

        studentClassRepository.save(new StudentClass(denis, german));
        studentClassRepository.save(new StudentClass(josh, english));
        studentClassRepository.save(new StudentClass(fred, german));
    }
}
