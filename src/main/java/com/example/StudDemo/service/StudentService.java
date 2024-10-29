package com.example.StudDemo.service;

import com.example.StudDemo.entity.Student;
import com.example.StudDemo.repository.StudentRepository;
import com.example.StudDemo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByTeacherId(Long teacherId) {
        return studentRepository.findByTeacherId(teacherId);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }


}
