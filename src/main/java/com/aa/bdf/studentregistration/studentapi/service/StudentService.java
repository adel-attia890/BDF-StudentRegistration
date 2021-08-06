package com.aa.bdf.studentregistration.studentapi.service;

import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseDto;
import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseMapper;
import com.aa.bdf.studentregistration.courseapi.entity.Course;
import com.aa.bdf.studentregistration.courseapi.entity.repository.CourseRepository;
import com.aa.bdf.studentregistration.courseapi.service.CourseService;
import com.aa.bdf.studentregistration.studentapi.entity.Student;
import com.aa.bdf.studentregistration.studentapi.entity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired CourseRepository courseRepository;
    @Autowired CourseService courseService;
    @Autowired CourseMapper courseMapper;

    public List<CourseDto> getStudentCourses(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        List<CourseDto> studentCourses = new ArrayList<>();
        if (student.isPresent()) {
            studentCourses = courseMapper.toCourseDtoList(student.get().getCourses());
        }
        return studentCourses;
    }

    public List<Student> getAllStudent() {
        List<Student> studentsList = studentRepository.findAll();
        return studentsList;
    }

    public Student fetch(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent())
            return student.get();
        else
            return null;
    }


    public Student registerStudentCourses(Long studentId, List<CourseDto> coursesList) {
        Student student = fetch(studentId);
        if (student != null) {
            for (CourseDto courseDto : coursesList) {
                Course course = courseService.fetch(courseDto.getId());
                if (course != null && !student.getCourses().contains(course)) {
                    student.getCourses().add(course);
                    studentRepository.saveAndFlush(student);
                }
            }
        }

        return student;

    }

    public Student cancelCourseRegistration(Long studentId, List<CourseDto> coursesList) {
        Student student = fetch(studentId);
        if (student != null) {
            for (CourseDto courseDto : coursesList) {
                Course course = courseService.fetch(courseDto.getId());
                if (course != null) {
                    student.getCourses().remove(course);
                    studentRepository.saveAndFlush(student);
                }
            }
        }

        return student;
    }
}
