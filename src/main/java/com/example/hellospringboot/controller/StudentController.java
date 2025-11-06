package com.example.hellospringboot.controller;

import com.example.hellospringboot.model.Student;
import com.example.hellospringboot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<Student>> getStudents(
            @RequestParam(value = "q",required = false) String q,
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable
            ) {
        Page<Student> page = (q == null || q.isBlank())
                ? studentService.findAll(pageable)
                : studentService.searchByNameOrEmail(q, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<Page<Student>> searchByName(
            @RequestParam String name,
            Pageable pageable
    ) {
        return ResponseEntity.ok(studentService.findByName(name, pageable));
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
