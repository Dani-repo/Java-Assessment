package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
        extends Person
        implements Evaluation {
    private double average;

    private double grade;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    public Student(String id, String name, String email, Date birthDate) {
        super(id, name, email, birthDate);
    }

    public void enrollToCourse(Course course) {
        // only if the course is not found in courses
        if (!courses.contains(course)) {
            // add the course to courses ArrayList
            courses.add(course);
            // put the course as an approved course in approvedCourses
            registerApprovedCourse(course);
        }

    }

    public void registerApprovedCourse(Course course) {
        approvedCourses.put(course.getCode(), course);
    }

    public boolean isCourseApproved(String courseCode) {
        // approvedCourses as a HashMap is stored in key:value pairs
        // what should isCourseApproved return true / false
        return approvedCourses.containsKey(courseCode);
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve. 
    public List<Course> findPassedCourses(Course course) {
        //TODO implement this method
        return null;
    }

    public boolean isAttendingCourse(String courseCode) {
        // If we use courses to check, what will the logic be?
        for (Course course : courses) {
            if (course.getCode() == courseCode)
                return true;
        }
        return false;
    }

    @Override
    public double getAverage() {
        return average;
    }

    public void setGrade(double grade) {
        this.grade += grade;        // compound the credits : 9 credits + 9 credits + ....

    }

    public double getGrade() {
        return grade;
    }

    @Override
    public List<Course> getApprovedCourses() {
        //TODO implement this method
        // return the courses ArrayList
        return courses;
    }

    @Override
    public String toString() {
        return "Student {" + super.toString() + "}";
    }
}
