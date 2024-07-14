import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Question> questions = new ArrayList<>();
        initializeQuestions(questions);
        int score = 0;

        for (Question question : questions) {
            System.out.println(question);
            char userAnswer = getUserAnswer(scanner, question);
            if (userAnswer == question.getCorrectOption()) {
                score++;
            }
        }

        displayResults(questions, score);
        scanner.close();
    }

    private static void initializeQuestions(ArrayList<Question> questions) {
        questions.add(new Question("What is the capital of France?", 
                new String[] { "A. Berlin", "B. Madrid", "C. Paris", "D. Rome" }, 'C'));
        questions.add(new Question("Which planet is known as the Red Planet?", 
                new String[] { "A. Earth", "B. Mars", "C. Jupiter", "D. Saturn" }, 'B'));
        questions.add(new Question("Who wrote 'To Kill a Mockingbird'?", 
                new String[] { "A. Harper Lee", "B. Mark Twain", "C. Jane Austen", "D. Charles Dickens" }, 'A'));
    }

    private static char getUserAnswer(Scanner scanner, Question question) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up!");
                System.exit(0);
            }
        };
        timer.schedule(task, 15000); // 15 seconds timer

        char answer = ' ';
        while (true) {
            System.out.print("Your answer: ");
            answer = scanner.next().toUpperCase().charAt(0);
            if (answer >= 'A' && answer <= 'D') {
                break;
            } else {
                System.out.println("Invalid option. Please enter A, B, C, or D.");
            }
        }
        timer.cancel();
        return answer;
    }

    private static void displayResults(ArrayList<Question> questions, int score) {
        System.out.println("\nQuiz Results:");
        System.out.println("Your Score: " + score + "/" + questions.size());
        for (Question question : questions) {
            System.out.println(question);
            System.out.println("Correct Answer: " + question.getCorrectOption());
        }
    }
}
