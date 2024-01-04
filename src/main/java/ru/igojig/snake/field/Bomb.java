package ru.igojig.snake.field;

import lombok.Getter;
import ru.igojig.snake.Coordinate;
import ru.igojig.snake.GameObject;
import ru.igojig.snake.statuses.FieldStatus;

import java.util.ArrayList;
import java.util.List;

public class Bomb implements GameObject {

    private List<Coordinate> bombsList;

    @Getter
    private FieldStatus fieldStatus = FieldStatus.BOMB;

    private int bombsCount;

    public Bomb(int bombsCount) {
        this.bombsCount=bombsCount;
        bombsList=new ArrayList<>();
    }

    public void generateBombs(List<Coordinate>... coordsOther){
        bombsList=generateObject(bombsCount, coordsOther);
    }


    public List<Coordinate> getCoordinates(){
        return bombsList;
    }



}
