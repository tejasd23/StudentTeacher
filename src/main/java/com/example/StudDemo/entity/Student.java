package com.example.StudDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private String subjectName;


    @OneToMany(mappedBy = "student", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<StudentTeacher> studentTeachers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacherId")
//    @JsonBackReference
    @JsonIgnoreProperties("students")
    private Teacher teacher;

    public void setTeacher(Teacher teacher) {
        if (this.teacher != null) {
            this.teacher.getStudents().remove(this);
        }
        this.teacher = teacher;
        if (teacher != null) {
            teacher.getStudents().add(this);
        }
    }

    //////
    public void addStudentTeacher(StudentTeacher studentTeacher) {
        studentTeachers.add(studentTeacher);
        studentTeacher.setStudent(this);
    }

    public void removeStudentTeacher(StudentTeacher studentTeacher) {
        studentTeachers.remove(studentTeacher);
        studentTeacher.setStudent(null);
    }
}
