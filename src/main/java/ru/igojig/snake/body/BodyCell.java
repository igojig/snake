package ru.igojig.snake.body;


import lombok.Data;
import ru.igojig.snake.Coord;

@Data
public class BodyCell {
    private SegmentStatus segmentStatus;
    private Coord coord;

    BodyCell(int x, int y){
        coord=new Coord(x,y);
        segmentStatus=SegmentStatus.SNAKE_BODY;
    }

    BodyCell(int x, int y, SegmentStatus segmentStatus){
        coord=new Coord(x,y);
        this.segmentStatus=segmentStatus;

    }

    BodyCell(Coord coord, SegmentStatus segmentStatus){
        this.coord=coord;
        this.segmentStatus=segmentStatus;
    }



}