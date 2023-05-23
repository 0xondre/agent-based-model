package org.example;

public class Murderer extends Citizen{
    public Murderer(int x, int y, int money){
        super(x, y, money);
    }
    public Murderer(int money){
        super(money);
    }

    @Override
    public char getSymbol() {
        return 'M';
    }

}
