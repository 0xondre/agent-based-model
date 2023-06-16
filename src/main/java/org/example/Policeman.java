package org.example;

/**
 * class describing policeman
 */
public class Policeman extends Normie {
    /**
     * Constructor used when we have to set specific coordinates
     */
    public Policeman(int x,int y,int money){
        super(x, y, money);
    }
    /**
     * Constructor used when we don't have to set specific coordinates
     */
    public Policeman(int money){
        super(money);

    }
    /**
     * return character representing policeman on board
     */
    @Override
    public char getSymbol() {
        return 'P';
    }
}
