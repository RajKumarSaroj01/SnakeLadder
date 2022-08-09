package com.java;

import java.util.UUID;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game(UUID.randomUUID().toString());
        game.create(4, 7);

        game.registerPlayer("Player1");
        game.registerPlayer("Player2");
        game.registerPlayer("Player3");
        game.registerPlayer("Player4");

        game.start();
    }
}
