package com.aa.bdf.studentregistration.courseapi.controller;

import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseDto;
import com.aa.bdf.studentregistration.courseapi.entity.Course;
import com.aa.bdf.studentregistration.courseapi.service.CourseService;
import com.aa.bdf.studentregistration.courseapi.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*/*")
@RestController
@RequestMapping(
        path = "/courses")
public class CourseController {
    @Autowired CourseService courseService;


    @GetMapping()
    public Object listCourses() {
        List<CourseDto> courseDtoList= courseService.getAllCoursesDto();
        if (!courseDtoList.isEmpty()){
            return new ResponseEntity(courseDtoList, HttpStatus.OK);
        }else {
            return new ResponseEntity("No courses found", HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping(value = "/export",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customersReport() throws IOException {
        List<Course> customers = courseService.getAllCourses();

        ByteArrayInputStream bis = PDFGenerator.generateCoursesPDFReport(customers);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Course_Schedule.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
