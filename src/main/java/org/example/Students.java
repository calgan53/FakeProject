package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;

    private static int nextId = 1;

    public Student(String studentId, String studentName, Gender gender, Address address, Department department, ArrayList<Course> registeredCourses) {
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = registeredCourses;
        this.studentId = String.format("S%06d", nextId);
        nextId++;
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);
        course.getRegisteredStudents().add(this);

        for (int i = 0; i < course.getAssignments().size(); i++) {
            course.getStudentScores(this).add(null);
        }

        return true;
    }

    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);
        course.getAllStudentScores().remove(this);

        return true;
    }

    public String toSimplifiedString() {
        return studentId + " | " + studentName + " | " + department.getDepartmentName();
    }

    public enum Gender {
        MALE, FEMALE
    }

    @Override
    public String toString() {
        return toSimplifiedString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(studentName, student.studentName) && Objects.equals(gender, student.gender) && Objects.equals(address, student.address) && Objects.equals(department, student.department) && Objects.equals(registeredCourses, student.registeredCourses);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Gender getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public Department getDepartment() {
        return department;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public static int getNextId() {
        return nextId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }
}
