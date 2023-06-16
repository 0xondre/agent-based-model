package org.example;

import java.util.Random;

/**
 * Class used to check if object is in the valid position and generate new objects on it
 */
public class Board {

    /**
     * Default board constructor
     */
    public Board() {

    }
    /**
     * Check if position is valid
     * @param x position x
     * @param y position y
     * @return boolean describing if position is on the board
     */
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < Options.BOARD_WIDTH && y >= 0 && y < Options.BOARD_HEIGHT;
    }
    /**
     * Print the board to the terminal
     * @param data board state
     */
    public void printBoard(char[][] data)  {
        for (int y = 0; y < Options.BOARD_HEIGHT; y++) {
            System.out.print("|");
            for (int x = 0; x < Options.BOARD_WIDTH; x++) {
                System.out.print(data[x][y]);
            }
            System.out.print("|");
            System.out.println();
        }
    }
    /**
     * Method used to generate new Citizens without specific coordinates
     * @return new Citizen object
     */
    public Citizen generateCitizen(){
        int money = new Random().nextInt(100);
        int occupation = new Random().nextInt(4);

        return switch (occupation) {
            case 0 -> new Normie(money);
            case 1 -> new Thief(money);
            case 2 -> new Policeman(money);
            case 3 -> new Murderer(money);
            default -> null;
        };
    }

    /**
     * Method used to generate new Citizens with specific coordinates
     * @param x position x
     * @param y position y
     * @return new Citizen object
     */
    public Citizen generateCitizen(int x, int y){
        int money = new Random().nextInt(100);
        int occupation = new Random().nextInt(4);

        return switch (occupation) {
            case 0 -> new Normie(x,y,money);
            case 1 -> new Thief(x,y,money);
            case 2 -> new Policeman(x,y,money);
            case 3 -> new Murderer(x,y,money);
            default -> null;
        };
    }

}
