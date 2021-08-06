package com.aa.bdf.studentregistration.studentapi.controller;

import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseDto;
import com.aa.bdf.studentregistration.studentapi.controller.dto.StudentDto;
import com.aa.bdf.studentregistration.studentapi.controller.dto.StudentMapper;
import com.aa.bdf.studentregistration.studentapi.entity.Student;
import com.aa.bdf.studentregistration.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*/*")
@RestController
@RequestMapping(
        path = "/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired StudentMapper studentMapper;

    @GetMapping()
    public Object listStudents() {
        List<StudentDto> studentsList =studentMapper.toStudentDtoList(studentService.getAllStudent());
        if (!studentsList.isEmpty()){
            return new ResponseEntity(studentsList, HttpStatus.OK);
        }else {
            return new ResponseEntity("No Students found", HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping(path = "/{studentId}/courses")
    public Object getStudentCourses(@PathVariable Long studentId) {
        List<CourseDto> courseDtoList= studentService.getStudentCourses(studentId);
        if (!courseDtoList.isEmpty()){
            return new ResponseEntity(courseDtoList, HttpStatus.OK);
        }else {
            return new ResponseEntity("No courses found", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(path = "/{studentId}/register")
    public Object registerStudentCourses(@PathVariable Long studentId, @RequestBody List<CourseDto> coursesList) {
        Student student = studentService.registerStudentCourses(studentId,coursesList);
        if(student != null)
            return new ResponseEntity("Student Registered to new Courses", HttpStatus.CREATED);

        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    @PostMapping(path = "/{studentId}/cancel")
    public Object cancelCourseRegistration(@PathVariable Long studentId, @RequestBody List<CourseDto> coursesList) {
        Student student = studentService.cancelCourseRegistration(studentId,coursesList);
        if(student != null)
            return new ResponseEntity("Cancelled courses registration", HttpStatus.NO_CONTENT);

        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
