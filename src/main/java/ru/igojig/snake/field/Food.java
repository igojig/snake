package ru.igojig.snake.field;


import lombok.Getter;
import ru.igojig.Main;
import ru.igojig.snake.Coord;

public class Food
{

    @Getter
    private Coord foodCord;

    public Food(int max_x, int max_y, int data_count) {

    }

    public void getNextFood(){
        foodCord=Coord.getRandomCoord(Main.WIDTH, Main.HEIGHT);
        while ()
    }


}
