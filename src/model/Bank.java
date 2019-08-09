package model;

public class Bank {
    private double cash;

    public Bank(int num) {
        this.cash = num * 1500;
    }

    public double getCash() {
        return this.cash;
    }

    public void changeCash(double cash) {
        this.cash += cash;
    }
}
