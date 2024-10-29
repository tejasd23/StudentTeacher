package com.example.StudDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Teachers")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teacherName;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Student> students;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentTeacher> studentTeachers = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        student.setTeacher(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setTeacher(null);
    }

    public void addStudentTeacher(StudentTeacher studentTeacher) {
        studentTeachers.add(studentTeacher);
        studentTeacher.setTeacher(this);
    }

    public void removeStudentTeacher(StudentTeacher studentTeacher) {
        studentTeachers.remove(studentTeacher);
        studentTeacher.setTeacher(null);
    }
}
