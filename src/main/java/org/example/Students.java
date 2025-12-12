package org.example;

import java.util.ArrayList;

public class Students {
    String studentId; // 6-digits starts with a character S. This id should be increased automatically.
    String studentName;
    Gender gender;// MALE FEMALE
    Address address;
    Department department;
    ArrayList<org.example.Course> registeredCourses;
    static int nextId;

    public boolean registerCourse(org.example.Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }
        return true;
    }

    public enum Gender {
        MALE, FEMALE
    }

}
