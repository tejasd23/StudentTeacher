package com.example.StudDemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="student_teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTeacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    public StudentTeacher(Student student, Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }

	public Student getTeacher() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStudent(Student student2) {
		// TODO Auto-generated method stub
		
	}

	public void setTeacher(Teacher teacher2) {
		// TODO Auto-generated method stub
		
	}
}
