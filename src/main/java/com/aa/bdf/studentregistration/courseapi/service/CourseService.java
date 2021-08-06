package com.aa.bdf.studentregistration.courseapi.service;


import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseDto;
import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseMapper;
import com.aa.bdf.studentregistration.courseapi.entity.Course;
import com.aa.bdf.studentregistration.courseapi.entity.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseMapper courseMapper;

    public List<CourseDto> getAllCoursesDto() {
        List<CourseDto> coursesDtoList = courseMapper.toCourseDtoList(courseRepository.findAll());
        return coursesDtoList;
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        return  courseList;
    }

    public Course fetch(Long courseId){
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent())
            return course.get();
        else
            return null;
    }
}
