package org.example;
import java.util.ArrayList;
import java.util.Random;

public class Assignment {

    private String assignmentId;
    private String assignmentName;
    private double weight;
    ArrayList<Integer> scores;
    static int nextId = 1;

    public Assignment(String assignmentName, double weight, ArrayList<Integer> scores) {
        this.assignmentId = String.valueOf(nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = scores;
    }

    public double calcAssignmentAvg() {
        if (scores.isEmpty()) return 0.0;

        double sum = 0;
        for (Integer s : scores) {
            if (s != null) sum += s;
        }
        return sum / scores.size();
    }

    public void generateRandomScore() {
        Random r = new Random();

        for (int i = 0; i < scores.size(); i++) {

            int roll = r.nextInt(11);

            int score;
            if (roll == 0) {
                score = r.nextInt(61);
            } else if (roll == 1 || roll == 2) {
                score = 60 + r.nextInt(11);
            } else if (roll == 3 || roll == 4) {
                score = 70 + r.nextInt(11);
            } else if (roll >= 5 && roll <= 8) {
                score = 80 + r.nextInt(11);
            } else {
                score = 90 + r.nextInt(11);
            }

            scores.set(i, score);
        }
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight + '}';
    }
}

