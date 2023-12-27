package ru.igojig.snake.field;


public class Food extends FieldObject
{


    public Food(int max_x, int max_y, int data_count) {
        super(max_x, max_y, data_count, FieldStatus.FOOD);
    }


}
