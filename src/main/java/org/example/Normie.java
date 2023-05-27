package org.example;

public class Normie extends Citizen{
    private boolean mated=false;
    public Normie(int x, int y, int money){
        super(x,y,money);

    }
    public Normie(int money){
        super(money);
    }

    public boolean HaveMated() {
        return mated;
    }

    public void Mated(boolean mated) {
        this.mated = true;
    }

    @Override
    public char getSymbol() {
        return 'N';
    }
}
