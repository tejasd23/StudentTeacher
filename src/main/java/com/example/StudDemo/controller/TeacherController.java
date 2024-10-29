package com.example.StudDemo.controller;

import com.example.StudDemo.entity.Student;
import com.example.StudDemo.entity.Teacher;
import com.example.StudDemo.repository.StudentRepository;
import com.example.StudDemo.repository.TeacherRepository;
import com.example.StudDemo.service.TeacherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllStudent(){
        return teacherService.getAllTeachers();
    }

//    @PostMapping
//    public Teacher createTeacher(@RequestBody Teacher teacher) {
//        return teacherService.createTeacher(teacher);
//    }
    ////////////////////////////////////////////

    @PostMapping
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher) {
        JSONObject jsonObject = new JSONObject(teacher);

        String teacherName = jsonObject.optString("teacherName");

        Teacher teach = new Teacher();
        teach.setTeacherName(teacherName);

        teacherService.createTeacher(teach);
        return new ResponseEntity<>("Teacher created successfully", HttpStatus.OK);
    }





    //////////////////////////////////////////////

    @GetMapping("/{id}")
    public Teacher findTeacherById(@PathVariable Long id) {
        return teacherService.findTeacherById(id);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteTeacher(@PathVariable Long id, Long studentId) {
//        teacherService.deleteTeacherById(id);
//        teacherService.deleteTeacherById(studentId);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/{id}/students")
    public Teacher addStudentToTeacher(@PathVariable Long id, @RequestBody Student student) {
        return teacherService.addStudentToTeacher(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacherAndStudentsById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        try {
            teacherService.deleteStudentById(studentId);
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
