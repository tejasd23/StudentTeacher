package com.example.StudDemo.repository;

import com.example.StudDemo.entity.Student;
import com.example.StudDemo.entity.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Long> {
    StudentTeacher findByStudent(Student student);
}
