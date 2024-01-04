package ru.igojig.snake.field;


import lombok.Getter;
import ru.igojig.snake.Coordinate;
import ru.igojig.snake.GameObject;
import ru.igojig.snake.statuses.FieldStatus;

import java.util.ArrayList;
import java.util.List;

public class Food implements GameObject {

    private List<Coordinate> foodsList;

    @Getter
    private FieldStatus fieldStatus = FieldStatus.FOOD;

    private int foodCount;

    public Food(int foodCount) {
        this.foodCount = foodCount;
        foodsList=new ArrayList<>();
    }

    public void generateFood(List<Coordinate>... coordsOther) {
        foodsList.clear();
        foodsList=generateObject(foodCount, coordsOther);
    }

    @Override
    public List<Coordinate> getCoordinates(){
        return foodsList;
    }


}
