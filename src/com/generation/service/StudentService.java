package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.*;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    // Generate dummy students
    public StudentService() {
        // a method that allows to add a student to students HashMap
        subscribeStudent(new Student("001", "John Doe", "johndoe@gmail.com", new Date("01/01/2000")));
        subscribeStudent(new Student("002", "May Fair", "mayfair@gmail.com", new Date("02/02/2010")));
        subscribeStudent(new Student("003", "Steve Smith", "stevesmith@gmail.com", new Date("03/03/2015")));
    }

    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student findStudent(String studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public boolean isSubscribed(String studentId) {
        // Check whether student is subscribed on this platform
        return findStudent(studentId) != null;
    }

    public void showSummary() {
        // show the student details and the enrolled courses.
        System.out.println("Enrolled Students");

        for (String key : students.keySet()) {  // key is the student ID
            // first outer for loop print student's info
            Student student = students.get(key);
            System.out.println(student);

            // the inner for loop print the courses enrolled by the student
            List<Course> enrolledCourses = student.getApprovedCourses();

            if (enrolledCourses.size() > 0) {
                System.out.println("\tEnrolled Courses");  // "\t" is for indentation

                for (Course course : enrolledCourses) {
                    System.out.println("\t" + course);
                }
            } else {
                System.out.println("\tNo course found");
            }
        }
    }

    public void enrollToCourse(String studentId, Course course) {
        if (students.containsKey(studentId)) {
            students.get(studentId).enrollToCourse(course);
        }
    }

    // gradeStudent: students are awarded credits if they are enrolled to courses.
    public void gradeStudent(String studentId, Course course) {

        // Find the student by the ID
        Student student = students.get(studentId);

        // if the student passed the course then give the student the credits
        // otherwise 0 credit is given.
        System.out.println( "│ Student Passed the course? (Y/N)        │" );
        String pass = "";
        Scanner scanner = new Scanner(System.in);
        String validGrade = "^[yYnN]";      //accept Y, y, N, or n
        do {
            pass = scanner.nextLine().trim();
            if (!pass.matches(validGrade)) {
                System.out.println("│ Invalid entry. Passed? (Y/N)            │");
            } else if (pass.equals("Y") || pass.equals("y")) {
                student.setGrade(course.getCredits());   // if passed - give credits
                break;
            } else {
                break;
            }
        }while (true) ;

        // Display total credits
        System.out.println("Total credits achieved by the student: "
                + studentId
                + " is "
                + student.getGrade()
        );
    }
}
