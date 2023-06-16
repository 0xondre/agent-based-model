package org.example;

/**
 * Class describing normie
 */
public class Normie extends Citizen{
    /**
     * Constructor used when we have to set specific coordinates
     * @param x position x
     * @param y position y
     * @param money amount of money to set
     */
    public Normie(int x, int y, int money){
        super(x,y,money);
    }
    /**
     * Constructor used when we don't have to set specific coordinates
     * @param money amount of money to set
     */
    public Normie(int money){
        super(money);
    }

    /**
     * Get character representing normie on board
     */
    @Override
    public char getSymbol() {
        return 'N';
    }
}
