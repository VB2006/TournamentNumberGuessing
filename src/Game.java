import java.util.concurrent.TimeUnit;
import java.util.*;
import java.security.SecureRandom;
public class Game {
//    private static TournamentSetup t;
    private static Participant player;
    private static Participant bots;

    private static Stack<Participant> s = new Stack<>();
    private static Scanner input;

    private static int score1;
    private static int playerBestScore;
    private static int ans;

    public static void main(String[] args) throws InterruptedException {
        input = new Scanner(System.in);
        System.out.println("Welcome to World of Games!");
        TimeUnit.SECONDS.sleep(1);
        intro();
    }

    public static void intro() throws InterruptedException {
        System.out.println("Do you want to play the game? (y/n)");
        String answer = input.nextLine();
        if (answer.equals("y")) {
            playGame();
        }
        else if (answer.equals("n")) {
            System.out.println("ok");
        }
        else {
            System.out.println("invalid response");
            intro();
        }
    }

    public static void playGame() throws InterruptedException{
        //       Scanner input = new Scanner(System.in);
        IDValidation();
        player = new Participant(ans);
        setBracket(64);
        explainGame();
        while (s.size() != 1) {
            int real = (int) (Math.random() * 100) + 1;
            System.out.println("What is your number?");
            int answer = input.nextInt();
            if (answer == real) {
                player.setScore(0);
                playerBestScore = 0;
                System.out.println("Good job! Your score is 0 for guessing correctly.");
            }
            else {
                player.setScore(Math.abs(real - answer));
                if (player.getScore() < playerBestScore) {
                    playerBestScore = player.getScore();
                }
                score1 += Math.abs(real - answer);
                System.out.println("Your score is: " + player.score);
                System.out.println("The number was: " + real);
            }
            updateBracket(player);
            if (s.contains(player)) {
                System.out.println("Congratulations! You made it to the next round");
            }
            else {
                System.out.println("Bad news... You have been eliminated. Thank you for playing and good luck next time!");
                break;
            }
            System.out.println("Do you want to see the tournament bracket currently? (y/n)");
            String a = input.next();
            if (a.equals("y")) {
                showTournamentBracket();
            }
            // do you want to see current leaderboard?
        }
        if (s.size() == 1) {
            System.out.println("Congratulations, " + player.getPlayerNum() + "! You won the entire tournament!");
        }
        System.out.println("Your final score was: " + score1 + "\n");
        System.out.println("For more statistics, press y. (Following statistics show future opponents)");
        String stats = input.next();
        if (stats.equals("y")) {
            if (s.size() != 1) {
                showWinner();
            }

        }
        System.out.println("Thank you for playing!");
        //show leaderboard for best players of tournament
        //show lowest scores of all time
    }

    private static void IDValidation() {
        System.out.println("What is your 5-digit ID?");
        ans = input.nextInt();
        if (ans > 99999 || ans < 10000) {
            System.out.println("Invalid. Enter a valid value.");
            IDValidation();
        }
    }

    private static void explainGame() throws InterruptedException {
        System.out.println("You must guess if the number is higher, lower, or equal to the given number in range 1-100.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Points are as follows:\n");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("The farther your guess is from the number, the more points you score. You are competing against another person in each of the rounds.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("The less points you have, the better. Good luck!\n");
    }

    public static void setBracket(int length) {
        s.push(player);
        for (int i = 0; i < length - 1; i++) {
            SecureRandom random = new SecureRandom();
            int rand = random.nextInt(99999) + 10000;
            bots = new Participant(rand);
            bots.setScore((int) (Math.random() * 100) + 1);
            s.push(bots);
        }
    }

    public static void showTournamentBracket() {
        Stack<Participant> aStack = new Stack<>();
        while (s.size() != 0) {
            Participant p1 = s.pop();
            System.out.println("ID: " + p1.getPlayerNum() + "\nScore: " + p1.getScore() + "\n");
            aStack.push(p1);
        }
        s = aStack;
    }

    public static void updateBracket(Participant c1) {
        Stack<Participant> copy = new Stack<>();
        Participant c2 = new Participant();
        while (s.size() != 0 && s.size() != 1) {
            c2 = s.pop();
            c1 = s.pop();
            if (c1.getScore() <= c2.getScore()) {
                copy.push(c1);
            }
            else {
                copy.push(c2);
            }
        }
        System.out.println("Your opponent " + c2.getPlayerNum() + " scored: " + c2.getScore());
        s = copy;
    }

    public static void showWinner() {
        while (s.size() != 1) {
            updateBracket(player);
            if (s.size() == 1) {
                Participant winner = s.pop();
                System.out.println("The winner was: " + winner.getPlayerNum() + " with a score of: " + winner.getScore() + "\n");
                break;
            }
        }
    }
}
