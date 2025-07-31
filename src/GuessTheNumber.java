import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GuessTheNumber {
    static boolean TimeUp = false;
    static int remainTime = 30;
    static Timer timer = new Timer();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        System.out.println("Select number range:");
        System.out.println("1. 1 - 100");
        System.out.println("2. 1 - 1000");
        int maxValue = 100;
        int chosenrange = scanner.nextInt();

        if (chosenrange == 2) {
            maxValue = 1000;
        }

        int targetNumber = random.nextInt(maxValue) + 1;
        int GuessRights = 10;


        System.out.println("you have seconds  " +  remainTime + "  to guess and " + GuessRights + " guess rights!");
        System.out.println("Started");


        StartCountDown();

        while (!TimeUp && GuessRights > 0) {
            System.out.print("Enter your guess: ");
            int UserGuess;


            if (scanner.hasNextInt()) {
                UserGuess = scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Enter a valid number.");
                continue;
            }

            GuessRights--;

            if (UserGuess == targetNumber) {
                System.out.println("your guess is correct you win!");
                timer.cancel();
                return;
            } else if (UserGuess < targetNumber) {
                System.out.println("to low!");
            } else {
                System.out.println("too high!");
            }

            System.out.println("remain guess: " + GuessRights + " |remain time: " + remainTime+ " seconds");
        }

        timer.cancel();

        if (TimeUp) {
            System.out.println("You are out of time");
        } else {
            System.out.println("You are out of guesses!");
        }

        System.out.println("the target number is: " + targetNumber);
    }


    public static void StartCountDown() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                remainTime--;
                if (remainTime <= 0) {
                    TimeUp = true;
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }
}
