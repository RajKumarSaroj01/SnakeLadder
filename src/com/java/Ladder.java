package com.java;

public class Ladder {

    private Integer tail;
    private Integer head;

    public Ladder(Integer head, Integer tail) {
        this.head = head;
        this.tail = tail;
    }

    public Integer getTail() {
        return tail;
    }

    public void setTail(Integer tail) {
        this.tail = tail;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }
}
