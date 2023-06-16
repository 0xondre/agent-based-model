package org.example;

// class describing normie
public class Normie extends Citizen{
    // Constructor used when we have to set specific coordinates
    public Normie(int x, int y, int money){
        super(x,y,money);
    }
    // Constructor used when we don't have to set specific coordinates
    public Normie(int money){
        super(money);
    }

    // return character representing normie on board
    @Override
    public char getSymbol() {
        return 'N';
    }
}
