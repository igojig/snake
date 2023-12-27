package ru.igojig.snake.field;

public class Bomb extends FieldObject
{

    public Bomb(int width, int height, int dataCount) {
        super(width, height, dataCount, FieldObjectStatus.BOMB);
    }


}
