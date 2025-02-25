import java.util.Scanner;

// Random Number Generate
class RandNo {
    public int generate(int max, int min) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}

public class NumberGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RandNo rnum = new RandNo();
        int totalAttempt = 0;
        int win = 0;

        while (true) {
            System.out.print("Enter the Maximum Number: ");
            int max = sc.nextInt();
            System.out.print("Enter the Minimum Number: ");
            int min = sc.nextInt();

            int cNum = rnum.generate(max, min);
            int attempt = 0;

            while (true) {
                System.out.print("Guess a number between " + min + " and " + max + ": ");
                int guessNo = sc.nextInt();
                attempt++;

                if (guessNo > cNum) {
                    System.out.println("Too high! Try again.");
                } else if (guessNo < cNum) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed it right.");
                    win++;
                    break;
                }
            }

            totalAttempt += attempt;
            System.out.println("Attempts: " + attempt);
            System.out.println("Wins: " + win);

            double winRate = (double) win / totalAttempt * 100;
            System.out.printf("Your win rate is: %.2f%%\n", winRate);

            System.out.print("Do you want to play again (yes/no)? ");
            String playAgain = sc.next().toLowerCase();

            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }
        }

        sc.close();
    }
}
