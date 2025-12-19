package com.generation.test;

import com.generation.model.Student;
import com.generation.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;   // Declaring the service to test

    @BeforeEach                              // Annotation: before each test is run
    void setUp(){                            // setUp instantiates studentService
        studentService = new StudentService();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Student 001, John Doe successfully found.")  // Annotation: display the description of the test
    void findStudent() {

        // Upon finding student 001
        Student student = studentService.findStudent("001");

        // succeed
        assertNotNull(student, "Student 001 is found.");
        assertEquals("John Doe", student.getName(), "Name of the student is John Doe.");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Student 001, 002, and 003 are subscribed to the platform")
    void isSubscribed() {
        assertTrue(studentService.isSubscribed("001"));
        assertTrue(studentService.isSubscribed("002"));
        assertTrue(studentService.isSubscribed("003"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Student 004 is NOT subscribed to the platform")
    void isNotSubscribed() {
        assertFalse(studentService.isSubscribed("004"));
    }
}