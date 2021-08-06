package com.aa.bdf.studentregistration.studentapi.controller.dto;

import com.aa.bdf.studentregistration.courseapi.controller.dto.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StudentDto implements Serializable {
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private List<CourseDto> courses;
}
