package ru.igojig.snake.body;


import ru.igojig.snake.Coord;

public class BodyCell extends Coord {
    SegmentStatus segmentStatus;

    BodyCell(int x, int y){
        super(x,y);
        segmentStatus=SegmentStatus.SNAKE_BODY;
    }

    BodyCell(int x, int y, SegmentStatus segmentStatus){
        super(x,y);
        this.segmentStatus=segmentStatus;

    }

    BodyCell(Coord coord, SegmentStatus segmentStatus){
        super(coord);
        this.segmentStatus=segmentStatus;
    }

    public SegmentStatus getSegmentStatus() {
        return segmentStatus;
    }

}