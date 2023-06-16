package org.example;

/**
 * Class describing thief
 */
public class Thief extends Citizen{
    /**
     * Constructor used when we have to set specific coordinates
     * @param x position x
     * @param y position y
     * @param money amount of money to set
     */
    public Thief(int x, int y, int money){
        super(x, y, money);
    }
    /**
     * Constructor used when we don't have to set specific coordinates
     * @param money amount of money to set
     */
    public Thief(int money){
        super(money);
    }
    /**
     * Return character representing thief on board
     */
    @Override
    public char getSymbol() {
        return 'T';
    }

}
