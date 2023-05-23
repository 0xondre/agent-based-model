package org.example;

public class Policeman extends Normie {
    public Policeman(int x,int y,int money){
        super(x, y, money);
    }
    public Policeman(int money){
        super(money);

    }
    @Override
    public char getSymbol() {
        return 'P';
    }
}
