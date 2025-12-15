package org.example;

import java.util.*;

public class Course {

    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;

    private static int nextId = 1;

    public Course(String courseId, String courseName, double credits, Department department, ArrayList<Assignment> assignments, ArrayList<Student> registeredStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = assignments;
        this.registeredStudents = registeredStudents;
    }

    public boolean isAssignmentWeightValid() {
        double sum = 0.0;
        for (Assignment a : assignments) {
            sum += a.weight;
        }
        return Math.abs(sum - 100.0) < 0.0001;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for (Assignment a : assignments) {
            a.calcAssignmentAvg();
        }
        return true;
    }


    public int[] calcStudentAverage() {
        int studentCount = registeredStudents.size();
        int[] averages = new int[studentCount];

        for (int s = 0; s < studentCount; s++) {
            double total = 0.0;

            for (Assignment a : assignments) {
                Double score = a.calcAssignmentAvg();
                if (score != null) {
                    double normalized = score / a.calcAssignmentAvg();
                    total += normalized * a.weight;
                }
            }

            averages[s] = (int) Math.round(total);
        }
        return averages;
    }

    public boolean addAssignment(String assignmentName, double weight) {
        Assignment assignment = new Assignment(assignmentName, weight);

        // add a null score for each already-registered student
        for (int i = 0; i < registeredStudents.size(); i++) {
            assignment.calcAssignmentAvg();
        }

        assignments.add(assignment);
        return true;
    }


    public void generateScores() {
        Random rand = new Random();

        for (Assignment a : assignments) {
            for (int i = 0; i < registeredStudents.size(); i++) {
                int score = rand.nextInt();
                a.calcAssignmentAvg();
            }
        }
    }

    public void displayScores() {
        System.out.println("Course: " + courseName + " (" + courseId + ")");

        System.out.printf("%-20s", "Student");
        for (Assignment a : assignments) {
            System.out.printf("%-12s", a.assignmentName);
        }

        System.out.printf("%-12s%n", "Final Score");

        int[] finalScores = calcStudentAverage();

        for (int i = 0; i < registeredStudents.size(); i++) {
            Student s = registeredStudents.get(i);
            System.out.printf("%-20s", s.getStudentName());

            for (Assignment a : assignments) {
                Double score = a.calcAssignmentAvg();
                System.out.printf("%-12s", score == null ? "-" : score.intValue());
            }
            System.out.printf("%-12d%n", finalScores[i]);
        }

        System.out.printf("%-20s", "Average");
        for (Assignment a : assignments) {
            // calcAssignmentAvg() already returns the average score
            double avg = a.calcAssignmentAvg();
            System.out.printf("%-12d", Math.round(avg));
        }
        System.out.println();

        System.out.println();
    }

    public String toSimplifiedString() {
        return courseId + ", " + courseName + ", " + credits + ", " + department.getDepartmentName();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course ID: ").append(courseId).append("\n");
        sb.append("Course Name: ").append(courseName).append("\n");
        sb.append("Credits: ").append(credits).append("\n");
        sb.append("Department: ").append(department.getDepartmentName()).append("\n");

        sb.append("Assignments:\n");
        for (Assignment a : assignments) {
            sb.append("  ").append(a).append("\n");
        }

        sb.append("Registered Students:\n");
        for (Student s : registeredStudents) {
            sb.append("  ")
                    .append(s.getStudentId())
                    .append(" - ")
                    .append(s.getStudentName())
                    .append("\n");
        }

        sb.append("Assignment Weights Valid: ")
                .append(isAssignmentWeightValid());

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Double.compare(credits, course.credits) == 0 && Objects.equals(courseId, course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(department, course.department) && Objects.equals(assignments, course.assignments) && Objects.equals(registeredStudents, course.registeredStudents);
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCredits() {
        return credits;
    }

    public Department getDepartment() {
        return department;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public static int getNextId() {
        return nextId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setRegisteredStudents(ArrayList<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public static void setNextId(int nextId) {
        Course.nextId = nextId;
    }
}

