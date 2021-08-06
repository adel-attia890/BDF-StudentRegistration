package com.aa.bdf.studentregistration;

import com.aa.bdf.studentregistration.authapi.entity.UserEntity;
import com.aa.bdf.studentregistration.authapi.entity.repository.UserRepository;
import com.aa.bdf.studentregistration.courseapi.entity.Course;
import com.aa.bdf.studentregistration.courseapi.entity.repository.CourseRepository;
import com.aa.bdf.studentregistration.studentapi.entity.Student;
import com.aa.bdf.studentregistration.studentapi.entity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TestDataGenerator {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository repository;

    public void initUsers() {
        List<UserEntity> users = Stream.of(
                new UserEntity(101L, "admin", "admin", "adminuser@bdf.com"),
                new UserEntity(102L, "user1", "pwd1", "user1@bdf.com"),
                new UserEntity(103L, "user2", "pwd2", "user2@bdf.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }

    public void initStudentCoursesData() throws ParseException {
        Student st1= new Student("admin","Adel", "Attia", "Cairo", "123435465");
        Student st2= new Student("user1","Mo", "Salah", "Zag", "456547");
        Student st3= new Student("user2","John", "Obama", "USA", "546456");

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Course springBootCourse = new Course("Spring Boot", "SpringBoot Course", simpleDateFormat.parse("12-10-2021"));
        Course hibernateCourse = new Course("Hibernate", "Hibernate Course", simpleDateFormat.parse("01-01-2022"));
        Course angularCourse = new Course("Angular", "Angular Course", simpleDateFormat.parse("15-12-2021"));
        Course algorithmsCourse = new Course("Algorithms", "Algorithms Course",simpleDateFormat.parse("22-09-2021"));
        Course microServicesCourse = new Course("MicroServices", "MicroServices Course", simpleDateFormat.parse("01-09-2021"));
        Course javaCourse = new Course("Java", "Java Course", new Date());

        List<Course> courseList = Arrays.asList(algorithmsCourse,microServicesCourse,javaCourse,algorithmsCourse, javaCourse);
//		courseRepository.saveAll(courseList);

        st1.getCourses().add(javaCourse);
        st1.getCourses().add(springBootCourse);
        st1.getCourses().add(hibernateCourse);
        st2.getCourses().add(angularCourse);
        st2.getCourses().add(algorithmsCourse);
        st3.getCourses().add(angularCourse);
        st3.getCourses().add(microServicesCourse);

        List<Student> studentList = Arrays.asList(st1,st2,st3);
        studentRepository.saveAll(studentList);
    }
}
