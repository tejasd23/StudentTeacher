package com.example.StudDemo.service;

import com.example.StudDemo.entity.Student;
import com.example.StudDemo.entity.Teacher;
import com.example.StudDemo.repository.StudentRepository;
import com.example.StudDemo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Teacher getTeacherById(Long id) {
        return teacherRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher createTeacher(Teacher teacher){
        return teacherRepository.save(teacher);

    }
    public Teacher findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElse(null);
    }


    public Teacher addStudentToTeacher(Long teacherId, Student student) {
        Teacher teacher = getTeacherById(teacherId);
        teacher.addStudent(student);
        return teacherRepository.save(teacher);
    }


    public void deleteTeacherAndStudentsById(Long teacherId) {
        studentRepository.deleteById(teacherId);
        teacherRepository.deleteById(teacherId);
    }

    public void deleteStudentById(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new RuntimeException("Student not found with id: " + studentId);
        }
    }

}
