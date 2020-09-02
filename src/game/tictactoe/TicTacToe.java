package game.tictactoe;
import java.util.*;

public class TicTacToe {
    static Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    public static void main(String[] args) {
        System.out.println("--------------------------------");
        System.out.println("Hi friend! Welcome to TicTacToe!");
        System.out.println("--------------------------------");
        while (true) {
            System.out.println("Do you want to play the game?\nInput your answer into the console (Y/N)");
            char readyToPlay = scanner.next().charAt(0);
            if (readyToPlay == 'Y' || readyToPlay == 'y') {
                while (true) {
                    System.out.println("Do you want to fight computer or your friend?\nInput your answer into the console (C/F)\nIf you want to come back the main menu input M");
                    char opponent = scanner.next().charAt(0);
                    if (opponent == 'C' || opponent == 'c') {
                        versusBot();
                    } else if (opponent == 'F' || opponent == 'f') {
                        versusFriend();
                    } else if (opponent == 'M' || opponent == 'm') {
                        break;
                    } else {
                        System.out.println("Wrong letter. C or F or M only.");
                    }
                }
            } else if (readyToPlay == 'N' || readyToPlay == 'n') {
                System.out.println("Thank you for playing our game.");
                break;
            } else {
                System.out.println("Wrong letter. Y or N only.");
            }
        }
    }
    public static void versusBot () {
        int botWinCount = 0;
        int userWinCount = 0;
        String winner;
        System.out.println("What's your name (or nickname)? Input your answer into the console.");
        String userNickname = scanner.next();
        do {
            System.out.println("Are you picking X or O. Input your answer into the console (X/O).");
            char symbolPick = scanner.next().charAt(0);
            while (symbolPick != 'X' && symbolPick != 'x' && symbolPick != 'O' && symbolPick != 'o') {
                System.out.println("Wrong letter. Use X or O.");
                symbolPick = scanner.next().charAt(0);
            }
            if (symbolPick == 'X' || symbolPick == 'x') {
                System.out.println("Alrighty, let's get started!");
                winner = playGame(createBoard(), userNickname, "Bot");
                if (winner.equals(userNickname)) {
                    userWinCount++;
                } else if (winner.equals("Bot")) {
                    botWinCount++;
                }
                System.out.println(userNickname + " has won " + userWinCount + " times.");
                System.out.println("Bot has won " + botWinCount + " times.");
            } else {
                System.out.println("Alrighty, let's get started!");
                winner = playGame(createBoard(), "Bot", userNickname);
                if (winner.equals(userNickname)) {
                    userWinCount++;
                } else if (winner.equals("Bot")) {
                    botWinCount++;
                }
                System.out.println(userNickname + " has won " + userWinCount + " times.");
                System.out.println("Bot has won " + botWinCount + " times.");
            }
        } while (rematchGame());
    }

    public static void versusFriend() {
        String winnerFinder;
        int userOneWinCount = 0;
        int userTwoWinCount = 0;
        System.out.println("What is user one's (1) name?");
        String userOne = scanner.next();
        System.out.println("What is user two's (2) name?");
        String userTwo = scanner.next();
        do {
            System.out.println("Who is picking X this round? (1/2)");
            int decision = scanner.nextInt();
            while (decision != 1 && decision != 2) {
                System.out.println("Wrong integer. 1 or 2 only.");
                decision = scanner.nextInt();
            }

            if (decision == 1) {
                winnerFinder = playGame(createBoard(), userOne, userTwo);
            } else {
                winnerFinder = playGame(createBoard(), userTwo, userOne);
            }

            if (winnerFinder.equals(userOne)) {
                userOneWinCount++;
            } else if (winnerFinder.equals(userTwo)) {
                userTwoWinCount++;
            }
            System.out.println(userOne + " has won " + userOneWinCount + " times.");
            System.out.println(userTwo + " has won " + userTwoWinCount + " times.");
        } while (rematchGame());
    }

    public static String playGame(String[][] board, String userOne, String userTwo) {
        ArrayList<Integer> playerOneCells = new ArrayList<>();
        ArrayList<Integer> playerTwoCells = new ArrayList<>();
        ArrayList<Integer> cellPool = allowedCells();
        String userName;
        int userInput;
        char symbol;
        int cellsTaken = 0;
        int whoWon = 0;
        Random rand = new Random();
        while (true) {
            if ((cellsTaken % 2) == 0) {
                symbol = 'X';
                if (userOne.equals("Bot")) {
                    userName = "Bot";
                        userInput = rand.nextInt(9);
                        while (playerTwoCells.contains(userInput) || playerOneCells.contains(userInput) || !cellPool.contains(userInput)) {
                            userInput = rand.nextInt(9);
                        }
                    System.out.println("AI picked cell #" + userInput);
                } else {
                    userName = userOne;
                    System.out.println(userName + ", please enter your placement (1-9)");
                    userInput = scanner.nextInt();
                    while (playerTwoCells.contains(userInput) || playerOneCells.contains(userInput) || !cellPool.contains(userInput)) {
                        if (!cellPool.contains(userInput)) {
                            System.out.println("This number is out of range. Pick a number from 1 to 9.");
                        } else {
                            System.out.println("This cell is already taken. Choose another one!");
                        }
                        userInput = scanner.nextInt();
                    }
                }
                playerOneCells.add(userInput);

            } else {
                symbol = 'O';
                if (userTwo.equals("Bot")) {
                    userName = "Bot";
                    userInput = rand.nextInt(9);
                    while (playerTwoCells.contains(userInput) || playerOneCells.contains(userInput) || !cellPool.contains(userInput)) {
                        userInput = rand.nextInt(9);
                    }
                    System.out.println("AI picked cell #" + userInput);
                } else {
                    userName = userTwo;
                    System.out.println(userName + ", please enter your placement (1-9)");
                    userInput = scanner.nextInt();
                    while (playerTwoCells.contains(userInput) || playerOneCells.contains(userInput) || !cellPool.contains(userInput)) {
                        if(!cellPool.contains(userInput)) {
                            System.out.println("This number is out of range. Pick a number from 1 to 9.");
                        } else {
                            System.out.println("This cell is already taken. Choose another one!");
                        }
                        userInput = scanner.nextInt();
                    }
                }
                playerTwoCells.add(userInput);
            }
            if (userInput == 1) {
                board[0][0] = "  " + symbol + " ";
                cellsTaken++;
            } else if (userInput == 2) {
                board[0][2] = "  " + symbol + "  ";
                cellsTaken++;
            } else if (userInput == 3) {
                board[0][4] = " " + symbol + "  ";
                cellsTaken++;
            } else if (userInput == 4) {
                board[2][0] = "  " + symbol + " ";
                cellsTaken++;
            } else if (userInput == 5) {
                board[2][2] = "  " + symbol + "  ";
                cellsTaken++;
            } else if (userInput == 6) {
                board[2][4] = " " + symbol + "  ";
                cellsTaken++;
            } else if (userInput == 7) {
                board[4][0] = "  " + symbol + " ";
                cellsTaken++;
            } else if (userInput == 8) {
                board[4][2] = "  " + symbol + "  ";
                cellsTaken++;
            } else if (userInput == 9) {
                board[4][4] = " " + symbol + "  ";
                cellsTaken++;
            }

            printBoard(board);
            if (userName.equals(userOne)) {
                if (winnerCheck(userName, playerOneCells)) {
                    whoWon = 1;
                    break;
                }
            } else if (userName.equals(userTwo)) {
                if (winnerCheck(userName, playerTwoCells)) {
                    whoWon = 2;
                    break;
                }
            }
            if (cellsTaken == 9) {
                System.out.println("It's a tie!");
                break;
            }
        }
        if (whoWon == 1) {
            return userOne;
        } else if (whoWon == 2) {
            return userTwo;
        } else {
            return " ";
        }
    }

    public static boolean winnerCheck(String userName, ArrayList<Integer> playersCells) {
        for (int i = 0; i < winCombination().size() ; i++) {
            if (playersCells.containsAll(winCombination().get(i))) {
                System.out.println("Winner Winner Chicken Dinner! " + userName + " won this round!");
                return true;
            }
        }
        return false;
    }

    public static boolean rematchGame() {
        System.out.println("Rematch? (input Y)\nTo come back to the previous menu input M");
        char rematch = scanner.next().charAt(0);
        while (rematch != 'Y' && rematch != 'y' && rematch != 'M' && rematch != 'm') {
            System.out.println("Wrong letter. Y or M only.");
            rematch = scanner.next().charAt(0);
        }
        return rematch != 'M' && rematch != 'm';
    }

    public static String[][] createBoard() {
        return new String[][]{
                {"    ", "|", "     ", "|", "    "},
                {"----", "+", "-----", "+", "----"},
                {"    ", "|", "     ", "|", "    "},
                {"----", "+", "-----", "+", "----"},
                {"    ", "|", "     ", "|", "    "}
        };
    }

    public static void printBoard(String[][] board) {
        for (String[] strings : board) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }
    public static ArrayList<Integer> allowedCells() {
        ArrayList<Integer> cells = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            cells.add(i);
        }
        return cells;
    }

    public static List<List> winCombination() {

        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List centerCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List diagonal1 = Arrays.asList(1, 5, 9);
        List diagonal2 = Arrays.asList(7, 5, 3);

        List<List> winCondition = new ArrayList<>();

        winCondition.add(topRow);
        winCondition.add(middleRow);
        winCondition.add(bottomRow);
        winCondition.add(leftCol);
        winCondition.add(centerCol);
        winCondition.add(rightCol);
        winCondition.add(diagonal1);
        winCondition.add(diagonal2);

        return winCondition;
    }
}
