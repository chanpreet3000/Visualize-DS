package com.chanpreet.visualizeds.classes;

public class Pair {
    public final int first, second;

    public Pair() {
        this.first = 0;
        this.second = 0;
    }

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Pair p) {
        return (this.first == p.first && this.second == p.second);
    }
}
