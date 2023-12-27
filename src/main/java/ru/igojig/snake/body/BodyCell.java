package ru.igojig.snake.body;


import ru.igojig.snake.Coord;
import ru.igojig.snake.field.FieldStatus;

public class BodyCell extends Coord {
    FieldStatus segmentStatus;

    BodyCell(int x, int y){
        super(x,y);
        segmentStatus=FieldStatus.SNAKE_BODY;
    }

    BodyCell(int x, int y, FieldStatus segmentStatus){
        super(x,y);
        this.segmentStatus=segmentStatus;

    }

    BodyCell(Coord coord, FieldStatus segmentStatus){
        super(coord);
        this.segmentStatus=segmentStatus;
    }

    public FieldStatus getSegmentStatus() {
        return segmentStatus;
    }

}