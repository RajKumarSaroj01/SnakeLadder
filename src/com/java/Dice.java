package com.java;

import java.util.Random;

public class Dice {

    private Random random;

    public Dice() {
        this.random = new Random();
    }

    public int rollDice() {
        int temp = random.nextInt() % 6 + 1;
        return temp < 0 ? -temp : temp;
    }

}
