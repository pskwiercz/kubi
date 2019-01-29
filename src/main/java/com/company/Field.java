package com.company;

public class Field {

    private int id;
    private int dices;

    public Field() {
        this.id = 0;
        this.dices = 0;
    }

    public Field(int id, int dices) {
        this.id = id;
        this.dices = dices;
    }

    public int roll() {
        return (int) (Math.random() * 6) + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDices() {
        return dices;
    }

    public void setDices(int dices) {
        this.dices = dices;
    }
}
