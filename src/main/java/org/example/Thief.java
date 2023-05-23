package org.example;

public class Thief extends Citizen{
    public Thief(int x, int y, int money){
        super(x, y, money);
    }
    public Thief(int money){
        super(money);
    }

    @Override
    public char getSymbol() {
        return 'T';
    }

}
