package ru.igojig.snake.body;


import lombok.Data;
import ru.igojig.snake.Coordinate;
import ru.igojig.snake.statuses.FieldStatus;
@Data
public class SnakeCell {
    FieldStatus fieldStatus;
    Coordinate coordinate;

    SnakeCell(int x, int y){
        coordinate =new Coordinate(x,y);
        fieldStatus = FieldStatus.SNAKE_BODY;
    }

    SnakeCell(int x, int y, FieldStatus fieldStatus){
        coordinate =new Coordinate(x,y);
        this.fieldStatus = fieldStatus;

    }

    SnakeCell(Coordinate coordinate, FieldStatus fieldStatus){
        this.coordinate=new Coordinate(coordinate.getX(), coordinate.getY());
        this.fieldStatus = fieldStatus;
    }

    SnakeCell(Coordinate coordinate){
        this.coordinate = coordinate;
        this.fieldStatus = FieldStatus.SNAKE_BODY;
    }
}