package ru.igojig.snake.body;


import lombok.Data;
import ru.igojig.snake.Coord;
import ru.igojig.snake.field.FieldStatus;
@Data
public class BodyCell extends Coord {
    FieldStatus fieldStatus;

    BodyCell(int x, int y){
        super(x,y);
        fieldStatus =FieldStatus.SNAKE_BODY;
    }

    BodyCell(int x, int y, FieldStatus fieldStatus){
        super(x,y);
        this.fieldStatus = fieldStatus;

    }

    BodyCell(Coord coord, FieldStatus fieldStatus){
        super(coord);
        this.fieldStatus = fieldStatus;
    }



}