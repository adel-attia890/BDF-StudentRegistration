package com.aa.bdf.studentregistration.courseapi.controller.dto;

import com.aa.bdf.studentregistration.courseapi.entity.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {
    public List<CourseDto> toCourseDtoList(List<Course> courseList){
        List<CourseDto> courseDtoList = new ArrayList<>();
        if(courseList !=null) {
            for (Course course : courseList) {
                CourseDto courseDto = new CourseDto();
                courseDto.id = course.getId();
                courseDto.name = course.getCourseName();
                courseDto.description = course.getDescription();
                courseDtoList.add(courseDto);
            }
        }
        return courseDtoList;
    }
}
