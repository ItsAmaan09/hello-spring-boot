package com.example.hellospringboot.service;

import com.example.hellospringboot.exception.StudentNotFoundException;
import com.example.hellospringboot.model.Student;
import com.example.hellospringboot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found"));
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        return studentRepository.save(existing);
    }

    public Student deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        studentRepository.deleteById(id);
        return student;
    }
}
