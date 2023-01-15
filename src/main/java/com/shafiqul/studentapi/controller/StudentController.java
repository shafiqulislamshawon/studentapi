package com.shafiqul.studentapi.controller;

import com.shafiqul.studentapi.model.Student;
import com.shafiqul.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin() 
@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    /**
     * Get all the student
     *
     * @return ResponseEntity
     */
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getstudent() {
        try {
            return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the student by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        try {
            //check if student exist in database
            Student empObj = getStuRec(id);

            if (empObj != null) {
                return new ResponseEntity<>(empObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Create new student
     *
     * @param student
     * @return ResponseEntity
     */
    @PostMapping("/student")
    public ResponseEntity<Student> newStudent(@RequestBody Student student) {
        Student newStudent = studentRepository
                .save(Student.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .build());
        return new ResponseEntity<>(newStudent, HttpStatus.OK);
    }

    /**
     * Update student record by using it's id
     *
     * @param id
     * @param student
     * @return
     */
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {

        //check if student exist in database
        Student empObj = getStuRec(id);

        if (empObj != null) {
            empObj.setName(student.getName());
            empObj.setAddress(student.getAddress());
            return new ResponseEntity<>(studentRepository.save(empObj), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete student by Id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable("id") long id) {
        try {
            //check if student exist in database
            Student emp = getStuRec(id);

            if (emp != null) {
                studentRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Delete all student
     *
     * @return ResponseEntity
     */
    @DeleteMapping("/student")
    public ResponseEntity<HttpStatus> deleteAllstudent() {
        try {
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Method to get the student record by id
     *
     * @param id
     * @return student
     */
    private Student getStuRec(long id) {
        Optional<Student> empObj = studentRepository.findById(id);

        if (empObj.isPresent()) {
            return empObj.get();
        }
        return null;
    }

}
