package com.example.StudDemo.controller;

import com.example.StudDemo.entity.Student;
import com.example.StudDemo.entity.StudentTeacher;
import com.example.StudDemo.entity.Teacher;
import com.example.StudDemo.service.StudentService;
import com.example.StudDemo.service.StudentTeacherService;
import com.example.StudDemo.service.TeacherService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student-teacher")
public class StudentTeacherController {

    @Autowired
    private StudentTeacherService studentTeacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PutMapping("/{studentId}/change-teacher")
    public ResponseEntity<String> changeTeacherForStudent(@PathVariable Long studentId,
                                                          @RequestBody String requestBody){
        try{
            JSONObject jsonObject = new JSONObject(requestBody);

            Long newTeacherId = jsonObject.optLong("newTeacherId");

            Student student = studentService.findStudentById(studentId);
            if(studentId == null){
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }

            Teacher newTeacher = teacherService.findTeacherById(newTeacherId);
            if(newTeacher == null){
                return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
            }

            studentTeacherService.changeTeacherForStudent(studentId, newTeacherId);
            return new ResponseEntity<>("Teacher updated for student",HttpStatus.OK);
        }catch (JSONException e) {
            return new ResponseEntity<>("Invalid JSON format", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
