package ru.igojig.snake.field;

public class Bomb extends FieldObject
{

    public Bomb(int max_x, int max_y, int data_count) {
        super(max_x, max_y, data_count, FieldStatus.BOMB);
    }


}
