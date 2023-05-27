package org.example;
import java.util.Random;

import static org.example.Options.BOARD_HEIGHT;
import static org.example.Options.BOARD_WIDTH;

public abstract class Citizen {
    private int balance;
    private int posX;
    private int posY;
    private boolean mated;
    private final Board board = new Board();

    public Citizen(int x, int y,int money){
    this.balance=money;
    this.posX=x;
    this.posY=y;
    }
    public Citizen(int money){
        this.balance = money;
        this.posX = new Random().nextInt(BOARD_WIDTH);
        this.posY = new Random().nextInt(BOARD_HEIGHT);
    }

    public void move(){
        Random random = new Random();
        int direction = random.nextInt(4);

        // Update position
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

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int money) {
        this.balance+=money;
    }

    public abstract char getSymbol();


    public boolean haveMated() {
        return mated;
    }

    public void Mated() {
        this.mated = true;
    }
}
