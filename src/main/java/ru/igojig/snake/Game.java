package ru.igojig.snake;


import lombok.Getter;
import lombok.Setter;
import ru.igojig.snake.body.SnakeCell;
import ru.igojig.snake.body.Snake;

import ru.igojig.snake.field.Bomb;
import ru.igojig.snake.statuses.FieldStatus;
import ru.igojig.snake.field.Food;
import ru.igojig.snake.statuses.GameStatus;
import ru.igojig.snake.statuses.MoveStatus;

import java.util.List;

public class Game {
    @Setter
    private MoveStatus currentMoveStatus;

    private Bomb bombs;
    private Food foods;
    private Snake snake;

    @Getter
    private GameStatus gameStatus;

    public static int width;
    public static int height;
    private int foodCount;
    private int bombCount;


    public Game(int width, int height, int snakeLength, int foodCount, int bombCount) {
        restart(width, height, snakeLength, foodCount, bombCount);
    }

    public void restart(int width, int height, int snakeLength, int foodCount, int bombCount) {
        gameStatus = GameStatus.PLAYED;
        currentMoveStatus = MoveStatus.RIGHT;
        this.width = width;
        this.height = height;

        snake = new Snake(snakeLength);
        bombs = new Bomb(bombCount);
        foods = new Food(foodCount);

        bombs.generateBombs(snake.getCoordinates());
        foods.generateFood(snake.getCoordinates(), bombs.getCoordinates());

        this.bombCount = bombCount;
        this.foodCount = foodCount;
    }


    public List<SnakeCell> getSnake() {
        return snake.getSnakeCellList();
    }

    public List<Coordinate> getBombs() {
        return bombs.getCoordinates();
    }

    public List<Coordinate> getFoods() {
        return foods.getCoordinates();
    }

    public FieldStatus getBombStatus() {
        return bombs.getFieldStatus();
    }

    public FieldStatus getFoodStatus() {
        return foods.getFieldStatus();
    }


    public void move() {

        snake.move(currentMoveStatus);

        FieldStatus headStatus = FieldStatus.SNAKE_HEAD;

        if (!snake.isInsideArea())
            gameStatus = GameStatus.OUT_OF_AREA;

        if (snake.isOccupied(bombs.getCoordinates())) {
            gameStatus = GameStatus.BOMBED;
            headStatus = FieldStatus.SNAKE_HEAD_BOMBED;
        }

        if (snake.isSelfEat()) {
            gameStatus = GameStatus.SELF_EATED;
            headStatus = FieldStatus.SNAKE_HEAD_BOMBED;
        }


        if (snake.isOccupied(foods.getCoordinates())) {
            foods.generateFood(snake.getCoordinates(), bombs.getCoordinates());
            snake.increaseLength();
            headStatus = FieldStatus.SNAKE_HEAD_EATED;
        } else {
            snake.moveEndOfSnake();
        }

        snake.moveHeadOfSnake();
        snake.setHeadStatus(headStatus);
    }

    public int getSnakeLength() {
        return snake.getSnakeLength();
    }
}
