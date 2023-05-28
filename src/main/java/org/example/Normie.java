package org.example;

public class Normie extends Citizen{
    public Normie(int x, int y, int money){
        super(x,y,money);

    }
    public Normie(int money){
        super(money);
    }

    @Override
    public char getSymbol() {
        return 'N';
    }
}
