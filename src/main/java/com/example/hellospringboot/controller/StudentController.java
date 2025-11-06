package com.example.hellospringboot.controller;

import com.example.hellospringboot.model.Student;
import com.example.hellospringboot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid Student student) {
        return ResponseEntity.status(201).body(studentService.addStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id,@RequestBody @Valid Student student) {
        return ResponseEntity.status(200).body(studentService.updateStudent(id,student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}
