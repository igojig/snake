package ru.igojig.snake.field;

import lombok.Getter;
import ru.igojig.Main;
import ru.igojig.snake.Coord;

import java.util.List;

public class Bomb
{

    @Getter
    private List<Coord> bombsList;

    public Bomb(int col, int row, int bombsCount) {

    }

    public void setRandomBombs(int bombsCount){
        int count=0;
        while (count<bombsCount){
            Coord randomCoord = Coord.getRandomCoord(Main.WIDTH, Main.HEIGHT);
            if(bombsList.stream().noneMatch(c->c.equals(randomCoord))){
                bombsList.add(randomCoord);
                count++;
            }
        }
    }

    public boolean isOccupied(Coord coord){
        return bombsList.stream()
                .anyMatch(c->c.equals(coord));
    }


}
