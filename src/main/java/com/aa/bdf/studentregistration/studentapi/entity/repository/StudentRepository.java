package com.aa.bdf.studentregistration.studentapi.entity.repository;

import com.aa.bdf.studentregistration.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
