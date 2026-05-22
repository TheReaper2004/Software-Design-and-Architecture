import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private final Dice dice = new Dice();
    private final Board board = new Board();
    private final List<Player> players = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private int currentPlayerIndex = 0;

    public Game() {
        setupPlayers();
    }

    private void setupPlayers() {
        System.out.println("How many players do you want? Choose 2, 3, or 4:");

        int numberOfPlayers = scanner.nextInt();
        scanner.nextLine();

        if (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.println("Invalid choice. Defaulting to 4 players.");
            numberOfPlayers = 4;
        }

        players.add(new Player("Red", 1, 36));

        if (numberOfPlayers >= 2) {
            players.add(new Player("Blue", 31, 6));
        }

        if (numberOfPlayers >= 3) {
            players.add(new Player("Yellow", 36, 1));
        }

        if (numberOfPlayers == 4) {
            players.add(new Player("Green", 6, 31));
        }
    }

    public void play() {
        boolean gameOver = false;
        int totalTurns = 0;

        System.out.println();
        System.out.println("===== BOARD GAME =====");
        System.out.println("Board Size: 6x6");
        System.out.println("Players: " + players.size());
        System.out.println("Rules: hit rule and wormholes enabled");
        System.out.println("Press ENTER to play each turn.");
        System.out.println();
        System.out.println("Game State: Ready -> In Play");
        System.out.println();

        determineStartingPlayer();

        while (!gameOver) {
            displayBoard();

            System.out.println("Press ENTER for next turn...");
            scanner.nextLine();

            Player currentPlayer = players.get(currentPlayerIndex);

            int roll = dice.roll();
            int oldPosition = currentPlayer.getPosition();
            int targetPosition = currentPlayer.calculateNewPosition(roll);

            Player hitPlayer = findPlayerAtPosition(currentPlayer, targetPosition);

            System.out.println(currentPlayer.getName() + " rolls " + roll);

            if (hitPlayer != null) {
                System.out.println(currentPlayer.getName() + " hits " + hitPlayer.getName());
                System.out.println(currentPlayer.getName() + " loses turn and stays at " + oldPosition);
                currentPlayer.loseTurn();
            } else {
                currentPlayer.move(roll);

                int wormholePosition = board.checkWormhole(currentPlayer.getPosition());

                if (wormholePosition != -1) {
                    System.out.println(currentPlayer.getName() + " enters a wormhole!");
                    currentPlayer.setPosition(wormholePosition);
                }

                System.out.println(currentPlayer.getName()
                        + " moves from " + oldPosition
                        + " to " + currentPlayer.getPosition());
            }

            System.out.println(currentPlayer.getName()
                    + " total movement = " + currentPlayer.getTotalMovement());

            System.out.println(currentPlayer.getName()
                    + " turns taken = " + currentPlayer.getTurns());

            System.out.println();

            totalTurns++;

            if (currentPlayer.hasWon()) {
                System.out.println(currentPlayer.getName() + " WINS!");
                System.out.println("Game State: In Play -> Game Over");
                gameOver = true;
            }

            currentPlayerIndex++;

            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
        }

        displayBoard();
        printSummary(totalTurns);
    }

    private void determineStartingPlayer() {
        System.out.println("Determining who starts first...");
        System.out.println();

        int highestRoll = 0;

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int roll = dice.roll();

            System.out.println(player.getName() + " rolled " + roll);

            if (roll > highestRoll) {
                highestRoll = roll;
                currentPlayerIndex = i;
            }
        }

        System.out.println();
        System.out.println(players.get(currentPlayerIndex).getName() + " starts first!");
        System.out.println();
    }

    private void displayBoard() {
        System.out.println("===== CURRENT BOARD =====");

        int[][] boardNumbers = {
                {36, 35, 34, 33, 32, 31},
                {25, 26, 27, 28, 29, 30},
                {24, 23, 22, 21, 20, 19},
                {13, 14, 15, 16, 17, 18},
                {12, 11, 10, 9, 8, 7},
                {1, 2, 3, 4, 5, 6}
        };

        for (int row = 0; row < boardNumbers.length; row++) {
            for (int col = 0; col < boardNumbers[row].length; col++) {
                int position = boardNumbers[row][col];
                String display = getPlayerSymbolAtPosition(position);

                if (display.equals("")) {
                    display = String.valueOf(position);
                }

                System.out.printf("[%3s]", display);
            }

            System.out.println();
        }

        System.out.println("R = Red, B = Blue, Y = Yellow, G = Green");
        System.out.println("Wormholes: 4 <-> 9 and 19 <-> 23");
        System.out.println();
    }

    private String getPlayerSymbolAtPosition(int position) {
        String symbols = "";

        for (Player player : players) {
            if (player.getPosition() == position) {
                symbols += player.getName().charAt(0);
            }
        }

        return symbols;
    }

    private Player findPlayerAtPosition(Player currentPlayer, int position) {
        for (Player player : players) {
            if (player != currentPlayer && player.getPosition() == position) {
                return player;
            }
        }

        return null;
    }

    private void printSummary(int totalTurns) {
        System.out.println();
        System.out.println("===== GAME SUMMARY =====");
        System.out.println("Total turns: " + totalTurns);
        System.out.println();

        System.out.println("| Player | Position | Turns | Movement |");
        System.out.println("|--------|----------|-------|----------|");

        for (Player player : players) {
            System.out.println("| "
                    + player.getName()
                    + " | "
                    + player.getPosition()
                    + " | "
                    + player.getTurns()
                    + " | "
                    + player.getTotalMovement()
                    + " |");
        }
    }
}