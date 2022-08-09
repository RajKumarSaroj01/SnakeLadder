package com.java;

import com.java.dto.Turn;
import com.java.exception.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.java.Constants.HIGHEST_POINT;
import static com.java.Constants.LOWEST_POINT;

public class Game {
    private String id;
    private List<Player> players;
    private Board board;
    private volatile Turn turn;
    private Dice dice;

    public Game(String id) {
        this.id = id;
        this.players = new ArrayList<>();
        this.turn = new Turn();
    }

    public void registerPlayer(Player player) {
        players.add(player);
    }

    public void registerPlayer(List<Player> player) {
        players.addAll(player);
    }

    public void registerPlayer(String name) {
        players.add(new Player(name, turn, board));
    }

    public void evictPlayer(Player player) {
        players.remove(player);
    }

    public void create(Integer numOfSnake, Integer numOfLadder) {
        if (numOfLadder < LOWEST_POINT || numOfLadder > HIGHEST_POINT || numOfSnake < LOWEST_POINT || numOfSnake > HIGHEST_POINT) {
            throw new CustomException("Not a valid exception");
        }

        this.dice = new Dice();
        board = new Board(UUID.randomUUID().toString(), dice);
        board.generate(numOfSnake, numOfLadder);
    }

    public void start() throws InterruptedException {
        if (players.size() < 2 || players.size() > 4) {
            throw new CustomException("should be in the range of 2 and 4");
        }
        turn.setLive(Boolean.TRUE);
        Boolean done = Boolean.TRUE;
        onBoardPlayer();
        System.out.println("starting the game");
        synchronized (turn) {
            while (done) {
                System.out.println("--------------------------------------");
                turn.wait(120);
                turn.setPlayerTurn(Boolean.FALSE);
                for (Player player : players) {
                    if (player.getCurrPosition() >= 100) {
                        System.out.println(" Winner is " + player.getName() + " total move " + player.getMoveCount());
                        done = Boolean.FALSE;
                        turn.setLive(Boolean.FALSE);
                    }
                }
                turn.setPlayerTurn(Boolean.TRUE);
                turn.notifyAll();
            }
        }
    }

    public void onBoardPlayer() {
        for (Player player : players) {
            (new Thread(new PlayerThread(player))).start();
        }
    }
}
