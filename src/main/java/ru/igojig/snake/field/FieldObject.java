package ru.igojig.snake.field;



import ru.igojig.snake.Coord;
import ru.igojig.snake.body.SnakeBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

 public class FieldObject {

     int width;
     int height;

    int dataCount;

    FieldStatus fieldStatus;

    List<Coord> workField;

    FieldObject(int width, int height, int dataCount, FieldStatus fieldStatus){

        restart(width, height, dataCount, fieldStatus);
    }

    void restart(int width, int height, int dataCount, FieldStatus fieldStatus){
        workField=new ArrayList<>();
        this.width =width;
        this.height =height;
        this.dataCount =dataCount;
        this.fieldStatus=fieldStatus;

    }



    public List<Coord> getWorkField(){
        return List.copyOf(workField);
    }

    public boolean isEmpty(int x, int y){
        return workField.stream().noneMatch(obj->obj.getX()==x && obj.getY()==y);
    }

    public void fillData(SnakeBody snakeBody, FieldObject... fieldObjects){
        int i=0;
        while (i< dataCount){
                generateGameObject(snakeBody, fieldObjects);
                i++;
            }
        }


    public void generateGameObject(SnakeBody snakeBody, FieldObject... fieldObjects){
        while (true){
            int x= ThreadLocalRandom.current().nextInt(width);
            int y=ThreadLocalRandom.current().nextInt(height);
            if( snakeBody.isEmpty(x, y)
                    && Arrays.stream(fieldObjects).allMatch(o->o.isEmpty(x,y))){
                workField.add(new Coord(x,y));
                break;
            }

        }
    }

    public void removeCell(Coord coord){
        workField.removeIf(o->o.equals(coord));
    }

    public FieldStatus getFieldStatus(){
        return fieldStatus;
    }



}
