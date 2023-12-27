package ru.igojig.snake.body;


import lombok.Data;
import ru.igojig.snake.Coord;
import ru.igojig.snake.field.FieldObjectStatus;
@Data
public class BodyCell extends Coord {
    FieldObjectStatus fieldObjectStatus;

    BodyCell(int x, int y){
        super(x,y);
        fieldObjectStatus = FieldObjectStatus.SNAKE_BODY;
    }

    BodyCell(int x, int y, FieldObjectStatus fieldObjectStatus){
        super(x,y);
        this.fieldObjectStatus = fieldObjectStatus;

    }



}