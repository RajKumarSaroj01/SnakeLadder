package com.java.dto;

public class Turn {

    private Boolean isPlayerTurn;
    private Boolean isLive;

    public Turn() {
        this.isPlayerTurn = Boolean.FALSE;
        this.isLive = Boolean.FALSE;
    }

    public Boolean getPlayerTurn() {
        return isPlayerTurn;
    }

    public void setPlayerTurn(Boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public Boolean getLive() {
        return isLive;
    }

    public void setLive(Boolean live) {
        isLive = live;
    }
}
