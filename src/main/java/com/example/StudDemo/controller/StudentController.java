package com.example.StudDemo.controller;

import com.example.StudDemo.entity.Student;
import com.example.StudDemo.entity.Teacher;
import com.example.StudDemo.repository.StudentRepository;
import com.example.StudDemo.service.StudentService;
import com.example.StudDemo.service.TeacherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/teacher/{id}")
    public List<Student> getStudentsByTeacherId(@PathVariable Long id) {
        return studentService.getStudentsByTeacherId(id);
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        JSONObject jsonObject = new JSONObject(student);

        String studentName = jsonObject.optString("studentName");
        String subjectName = jsonObject.optString("subjectName");
        Long teacherId = jsonObject.optLong("teacherId");

        Teacher teacher = teacherService.findTeacherById(teacherId);
        if(teacher == null){
            return new ResponseEntity<>("Teacher not found", HttpStatus.BAD_REQUEST);
        }

        Student stud = new Student();
        stud.setStudentName(studentName);
        stud.setSubjectName(subjectName);
        stud.setTeacher(teacher);

        studentService.createStudent(stud);
        return new ResponseEntity<>("Student created successfully", HttpStatus.OK);
//        return studentService.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }

}


