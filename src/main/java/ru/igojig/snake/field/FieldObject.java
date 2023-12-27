package ru.igojig.snake.field;



import ru.igojig.snake.Coord;
import ru.igojig.snake.body.SnakeBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

 public class FieldObject {

     int max_x;
     int max_y;

    int data_count;

    FieldStatus fieldStatus;

    List<Coord> workField;

    FieldObject(int max_x, int max_y, int data_count, FieldStatus fieldStatus){

        restart(max_x, max_y, data_count, fieldStatus);
    }

    void restart(int max_x, int max_y, int data_count, FieldStatus fieldStatus){
        workField=new ArrayList<>();
        this.max_x=max_x;
        this.max_y=max_y;
        this.data_count=data_count;
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
        while (i<data_count){
                generateGameObject(snakeBody, fieldObjects);
                i++;
            }
        }


    public void generateGameObject(SnakeBody snakeBody, FieldObject... fieldObjects){
        while (true){
            int x= ThreadLocalRandom.current().nextInt(max_x);
            int y=ThreadLocalRandom.current().nextInt(max_y);
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
