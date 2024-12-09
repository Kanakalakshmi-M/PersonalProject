package com.web.controllers;

import com.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.entity.Student;

import java.util.Optional;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @PostMapping()
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        System.out.println("In save student Method..");
        return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentData(@PathVariable long id){
        System.out.println("In getStudent Method..");
        Optional<Student> student = studentRepo.findById(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}
