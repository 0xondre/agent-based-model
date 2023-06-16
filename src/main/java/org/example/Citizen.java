package org.example;
import java.util.Random;

import static org.example.Options.BOARD_HEIGHT;
import static org.example.Options.BOARD_WIDTH;

/**
 * abstract class describing citizen
 */
public abstract class Citizen {
    /**
     * money balance of the citizen
     */
    private int balance;
    /**
     * position x of the object on the map
     */
    private int posX;
    /**
     * position y of the object on the map
     */
    private int posY;
    /**
     * mating boolean information
     */
    private boolean mated;
    /**
     * Board object used to check if animal is still on the map
     */
    private final Board board = new Board();
    /**
     * Constructor used when we have to set specific coordinates
     */
    public Citizen(int x, int y,int money){
    this.balance=money;
    this.posX=x;
    this.posY=y;
    }
    /**
     * Constructor used when we don't have to set specific coordinates
     */
    public Citizen(int money){
        this.balance = money;
        this.posX = new Random().nextInt(BOARD_WIDTH);
        this.posY = new Random().nextInt(BOARD_HEIGHT);
    }

    /**
     * Method used to move class across the board
     */
    public void move(){
        Random random = new Random();
        int direction = random.nextInt(4);

        switch (direction) {
            case 0 -> {  // Move up
                if (board.isValidPosition(posX, posY - 1)) {
                    posY--;
                }
            }
            case 1 -> {  // Move down
                if (board.isValidPosition(posX, posY + 1)) {
                    posY++;
                }
            }
            case 2 -> {  // Move left
                if (board.isValidPosition(posX - 1, posY)) {
                    posX--;
                }
            }
            case 3 -> {  // Move right
                if (board.isValidPosition(posX + 1, posY)) {
                    posX++;
                }
            }
        }

    }

    /**
     * Get position x of object on the board
     */
    public int getPosX() {
        return posX;
    }
    /**
     * Get position y of object on the board
     */
    public int getPosY() {
        return posY;
    }
    /**
     * Get balance of object
     */
    public int getBalance() {
        return balance;
    }
    /**
     * Add to object's balance
     */
    public void setBalance(int money) {
        this.balance+=money;
    }
    /**
     * Get symbol of object
     */
    public abstract char getSymbol();
    /**
     * Get boolean about object mating
     */
    public boolean haveMated() {
        return mated;
    }
    /**
     * Set boolean mated to true
     */
    public void Mated() {
        this.mated = true;
    }
}
