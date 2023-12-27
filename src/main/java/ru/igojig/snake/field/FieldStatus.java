package ru.igojig.snake.field;

import java.awt.*;

public enum FieldStatus
{
    EMPTY(Color.BLACK),
    FOOD(Color.WHITE),
    BOMB(Color.RED);

    Color color;

    FieldStatus(Color color){
        this.color=color;
    }

    public Color getColor() {
        return color;
    }
}
