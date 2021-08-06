package com.aa.bdf.studentregistration.courseapi.entity.repository;

import com.aa.bdf.studentregistration.courseapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
