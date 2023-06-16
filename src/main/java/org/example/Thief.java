package org.example;

// class describing thief
public class Thief extends Citizen{
    // Constructor used when we have to set specific coordinates
    public Thief(int x, int y, int money){
        super(x, y, money);
    }
    // Constructor used when we don't have to set specific coordinates
    public Thief(int money){
        super(money);
    }
    // return character representing thief on board
    @Override
    public char getSymbol() {
        return 'T';
    }

}
