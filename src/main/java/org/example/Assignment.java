package org.example;
import java.util.ArrayList;
import java.util.Random;

public class Assignment {

    String assignmentId;
    String assignmentName;
    double weight;
    ArrayList<Integer> scores;
    static int nextId = 1;

    public Assignment(String assignmentName, double weight) {
        this.assignmentId = String.valueOf(nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = scores;
    }

    public double generateRandomScore(int numberOfStudents) {
        Random rand = new Random();
        scores.clear();

        for (int i = 0; i < numberOfStudents; i++) {

            int randomBucket = rand.nextInt(11);
            int score;

            if (randomBucket == 0) {
                score = rand.nextInt(61);           // [0, 60]
            } else if (randomBucket <= 2) {
                score = 60 + rand.nextInt(11);     // [60, 70]
            } else if (randomBucket <= 4) {
                score = 70 + rand.nextInt(11);     // [70, 80]
            } else if (randomBucket <= 8) {
                score = 80 + rand.nextInt(11);     // [80, 90]
            } else {
                score = 90 + rand.nextInt(11);     // [90, 100]
            }

            scores.add(score);
        }

        return calcAssignmentAvg();
    }


    public double calcAssignmentAvg() {
        if (scores.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (int score : scores) {
            sum += score;
        }

        return (double) sum / scores.size();
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight + '}';
    }
}

