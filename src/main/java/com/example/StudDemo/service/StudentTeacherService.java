package com.example.StudDemo.service;

import com.example.StudDemo.entity.Student;
import com.example.StudDemo.entity.StudentTeacher;
import com.example.StudDemo.entity.Teacher;
import com.example.StudDemo.repository.StudentRepository;
import com.example.StudDemo.repository.StudentTeacherRepository;
import com.example.StudDemo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentTeacherService {

    @Autowired
    private StudentTeacherRepository studentTeacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

//    @Transactional
//    public void changeTeacherForStudent(Student student, Teacher newTeacher) {
//        StudentTeacher studentTeacher = studentTeacherRepository.findByStudent(student);
//        if (studentTeacher != null) {
//            studentTeacher.setTeacher(newTeacher);
//            System.out.println("Saving StudentTeacher: " + studentTeacher);
//            studentTeacherRepository.save(studentTeacher);
//        }else {
//            System.out.println("No StudentTeacher found " + student.getId());
//        }
//        studentRepository.save(student);
//        teacherRepository.save(newTeacher);
//    }


    @Transactional
    public void changeTeacherForStudent(Long studentId, Long newTeacherId) {
        try{
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
            Teacher newTeacher = teacherRepository.findById(newTeacherId).orElseThrow(() -> new RuntimeException("Teacher not found with id: " + newTeacherId));

            StudentTeacher existingRelationship = studentTeacherRepository.findByStudent(student);
            if (existingRelationship != null) {
                student.removeStudentTeacher(existingRelationship);
                existingRelationship.getTeacher().removeStudentTeacher(existingRelationship);
//                studentTeacherRepository.delete(existingRelationship);
            } else {
                StudentTeacher newRelationship = new StudentTeacher(student, newTeacher);
                student.addStudentTeacher(newRelationship);
                newTeacher.addStudentTeacher(newRelationship);
                studentTeacherRepository.save(newRelationship);
            }
            student.setTeacher(newTeacher);
            studentRepository.save(student);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error changing teacher for student: " + e.getMessage());
        }
    }
}




























// Update or create the StudentTeacher relationship
//        StudentTeacher existingRelationship = studentTeacherRepository.findByStudent(student);
//
//        if (existingRelationship != null) {
//            // Update existing relationship
//            existingRelationship.setTeacher(newTeacher);
//            studentTeacherRepository.save(existingRelationship);
//        } else {
//            // Create new relationship
//            StudentTeacher newRelationship = new StudentTeacher(student, newTeacher);
//            student.addStudentTeacher(newRelationship);
//            newTeacher.addStudentTeacher(newRelationship);
//            studentTeacherRepository.save(newRelationship);
//        }
//        teacherRepository.save(newTeacher);