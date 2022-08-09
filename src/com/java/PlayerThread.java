package com.java;

public class PlayerThread implements Runnable {

    private Player player;

    public PlayerThread(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            player.play();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
