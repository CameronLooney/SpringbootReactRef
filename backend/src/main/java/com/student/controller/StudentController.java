package com.student.controller;

import com.student.model.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StudentController {


    @Autowired
    private StudentService studentService;

    public StudentController(StudentService _studentService) {
        studentService = _studentService;
    }
    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student student){
        studentService.save(student);
        return "New Student Added: " + student;
    }
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        return studentService.findById(studentId);

    }

    @PutMapping("/students")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        studentService.save(student);
        return ResponseEntity.ok("Student updated successfully: " + student);

    }


    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        studentService.deleteById(studentId);
        return "Student with ID: " + studentId + " deleted successfully";

    }


}
