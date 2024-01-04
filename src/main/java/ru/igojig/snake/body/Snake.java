package ru.igojig.snake.body;


import lombok.Getter;
import ru.igojig.snake.Coordinate;
import ru.igojig.snake.Game;
import ru.igojig.snake.GameObject;
import ru.igojig.snake.statuses.MoveStatus;
import ru.igojig.snake.statuses.FieldStatus;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class Snake implements GameObject {

    @Getter
    int snakeLength;

    private Coordinate nextCoordinate;


    @Getter
    LinkedList<SnakeCell> snakeCellList;

    public Snake(int snakeLength) {
        restart(snakeLength);
    }

    public void restart(int snakeLength) {
        this.snakeLength = snakeLength;
        snakeCellList = new LinkedList<>();
        generateSnake();
    }


    public void generateSnake() {
        int y = Game.width / 2;
        int x = Game.height / 2 - snakeLength / 2;

        for (int i = 0; i < snakeLength; i++) {
            snakeCellList.add(new SnakeCell(x + i, y, FieldStatus.SNAKE_BODY));
        }
        snakeCellList.getLast().fieldStatus = FieldStatus.SNAKE_HEAD;
        snakeCellList.getFirst().fieldStatus = FieldStatus.SNAKE_TAIL;

        nextCoordinate = new Coordinate(x + snakeLength - 1, y);
    }

    @Override
    public List<Coordinate> getCoordinates() {
        return snakeCellList.stream().
                map(SnakeCell::getCoordinate).
                toList();
    }

    public void moveEndOfSnake() {
        snakeCellList.removeFirst();

    }

    public void moveHeadOfSnake() {
        snakeCellList.getLast().fieldStatus = FieldStatus.SNAKE_BODY;
        snakeCellList.add(new SnakeCell(nextCoordinate, FieldStatus.SNAKE_HEAD));
        snakeCellList.getFirst().fieldStatus = FieldStatus.SNAKE_TAIL;

    }

    public boolean isInsideArea() {
        return nextCoordinate.getX() >= 0 &&
                nextCoordinate.getX() < Game.width &&
                nextCoordinate.getY() >= 0 &&
                nextCoordinate.getY() < Game.height;
    }


    public boolean isSelfEat() {
        return snakeCellList.stream().
                map(SnakeCell::getCoordinate).
                anyMatch(c -> c.equals(nextCoordinate));
    }

    public void increaseLength() {
        snakeLength++;
    }


    public void setHeadStatus(FieldStatus fieldStatus) {
        snakeCellList.getLast().setFieldStatus(fieldStatus);
    }


    @SafeVarargs
    final public boolean isOccupied(List<Coordinate>... coords) {
        return Arrays.stream(coords).
                flatMap(Collection::stream).
                anyMatch(c -> c.equals(nextCoordinate));
    }

    public void move(MoveStatus currentMoveStatus) {
        currentMoveStatus.move(nextCoordinate);
    }
}
