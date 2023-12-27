package ru.igojig.snake;

import lombok.NoArgsConstructor;

import java.util.Objects;
@NoArgsConstructor
public class Coord {
   protected int x;
    protected int y;

    public Coord(int x, int y){
        this.x=x;
        this.y=y;
    }

public Coord(Coord coord){
        this.x=coord.x;
        this.y=coord.y;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coord coord)) return false;
        return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coord getCoord(){
        return this;
    }
}
