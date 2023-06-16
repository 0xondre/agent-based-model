package org.example;

/**
 * Class describing policeman
 */
public class Policeman extends Normie {
    /**
     * Constructor used when we have to set specific coordinates
     * @param x position x
     * @param y position y
     * @param money amount of money to set
     */
    public Policeman(int x,int y,int money){
        super(x, y, money);
    }
    /**
     * Constructor used when we don't have to set specific coordinates
     * @param money amount of money to set
     */
    public Policeman(int money){
        super(money);

    }
    /**
     * Return character representing policeman on board
     */
    @Override
    public char getSymbol() {
        return 'P';
    }
}
