package com.aa.bdf.studentregistration.studentapi.controller.dto;

import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseDto;
import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseMapper;
import com.aa.bdf.studentregistration.studentapi.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {
	@Autowired CourseMapper courseMapper;

	public List<StudentDto> toStudentDtoList(List<Student> studentList){
		List<StudentDto> studentDtoList = new ArrayList<>();
		if(studentList !=null) {
			for (Student student : studentList) {
				List<CourseDto> coursesDtoList = courseMapper.toCourseDtoList(student.getCourses());
				StudentDto studentDto = new StudentDto();
				studentDto.setId(student.getId());
				studentDto.setFirstName(student.getFirstName());
				studentDto.setLastName(student.getLastName());
				studentDto.setPhone(student.getPhone());
				studentDto.setAddress(student.getAddress());
				studentDto.setCourses(coursesDtoList);
				studentDtoList.add(studentDto);
			}
		}
		return studentDtoList;
	}
}
