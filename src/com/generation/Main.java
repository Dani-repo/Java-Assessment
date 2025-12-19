package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws ParseException {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registerStudent(studentService, scanner);
                    break;
                case 2:
                    findStudent(studentService, scanner);
                    break;
                case 3:
                    gradeStudent(studentService, scanner);
                    break;
                case 4:
                    enrollStudentToCourse(studentService, courseService, scanner);
                    break;
                case 5:
                    showStudentsSummary(studentService, scanner);
                    break;
                case 6:
                    showCoursesSummary(courseService, scanner);
                    break;
                default:
                    System.out.println("Invalid option. Please try again");
            }
        }
        while (option != 7);
    }


    public static Student createStudentMenu( Scanner scanner )
            throws ParseException
    {
        System.out.println( "┌──────────────────────────────────────────┐" );
        System.out.println( "│          1 Register Student              │" );
        System.out.println( "├──────────────────────────────────────────┤" );

        System.out.println( "│ Enter student name:                      │" );
        String name = "";
        // check for valid name;
        // rules: must not be empty + only alphabets are allowed + minimum 2 characters
        String validName = "^[a-zA-Z ]{2,}+$";
        scanner.nextLine();
        do {
            name = scanner.nextLine().trim();
            if (!name.matches(validName)) {
                System.out.println("│ Invalid name. Please try again:          │");
            } else
                break;
        } while (true);

        // check for valid student ID
        // rules: must not be empty + only numbers are allowed + must be 3 digits
        System.out.println( "│ Enter ID. Please enter 001-999           │" );
        String id = "";
        String validId = "^[0-9]{3}";
        do {
            id = scanner.nextLine().trim();
            if (!id.matches(validId)) {
                System.out.println("│ Invalid Student ID. Please try again.    │");
            } else
                break;
        } while (true);

        // check for valid email address
        // rules: must not be empty + must contain xxxx@xxx.xx
        System.out.println( "│ Enter student email address:             │");
        String email = null;
        String validEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        do {
            email = scanner.nextLine().trim();
            if (!email.matches(validEmail)) {
                System.out.println("│ Invalid email address. Please try again. │");
            } else
                break;
        } while (true);

        // check for valid date in mm/dd/yyyy format
        // rules: must not be empty + and in mm/dd/yyyy format.
        System.out.println( "│ Enter student birth date (mm/dd/yyyy)    │" );
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setLenient(false); // use strict validation
        Date birthDate = new Date();

        do {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("│ Invalid date. Enter in mm/dd/yyyy format.│");
                continue;
            }
            try {
                birthDate = formatter.parse(input);
                break;
            } catch (java.text.ParseException e) {
                System.out.println("│ Invalid date. Enter in mm/dd/yyyy format.│");
            }
        } while (true);

        System.out.println( "└──────────────────────────────────────────┘" );
        // print registration data
        Student student = new Student( id, name, email, birthDate );
        System.out.println( "Student Successfully Registered! " );
        System.out.println(student);
        return student;
    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,
                                              Scanner scanner) {
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        System.out.println(student);
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Invalid Course ID");
            return;
        }
        System.out.println(course);
        courseService.enrollStudent(courseId, student);
        studentService.enrollToCourse(studentId, course);
        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

    }

    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {
        courseService.showSummary();
    }

    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {
        studentService.showSummary();
    }

    private static void gradeStudent(StudentService studentService, Scanner scanner) {
        //TODO implement gradeStudent()
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);

        // First we find the student
        if (student != null) {
            System.out.println("Enter course code: ");
            String courseCode = scanner.next();
            boolean courseFound = student.isCourseApproved(courseCode);
            // then we find the course
            if (courseFound) {
                Course courseToGrade = null;

                for (Course c : student.getApprovedCourses()) {
                    if (c.getCode().equals(courseCode)) {
                        courseToGrade = c;
                    }
                }
                studentService.gradeStudent(studentId, courseToGrade);
            } else {
                System.out.println("Course with Code = " + courseCode + " not found ");
            }
        } else {
            System.out.println("Student with Id = " + studentId + " not found");
        }
    }

    private static void findStudent(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student != null) {
            System.out.println("Student Found: ");
         //   System.out.println(student);
            System.out.println(student.toString().replace("{", "").replace("}", ""));
        } else {
            System.out.println("Student with Id = " + studentId + " not found");
        }
    }

    private static void registerStudent(StudentService studentService, Scanner scanner)
            throws ParseException {
        Student student = createStudentMenu(scanner);
        studentService.subscribeStudent(student);
    }


}
