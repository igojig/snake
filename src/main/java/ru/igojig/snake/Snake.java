package ru.igojig.snake;


import lombok.Getter;
import lombok.Setter;
import ru.igojig.snake.body.BodyCell;
import ru.igojig.snake.body.SnakeBody;
import ru.igojig.snake.field.Bomb;
import ru.igojig.snake.field.FieldObjectStatus;
import ru.igojig.snake.field.Food;

import java.util.List;

public class Snake {
    @Setter
    MoveStatus currentMoveStatus;

    Bomb bombs;
    Food foods;
    SnakeBody snakeBody;

    @Getter
    GameStatus gameStatus;

    @Getter
    int snakeLength;
    int width;
    int height;
    int foodCount;
    int bombCount;

    boolean isEated = false;


    public Snake(int width, int height, int snakeLength, int foodCount, int bombCount) {


        restart(width, height, snakeLength, foodCount, bombCount);

    }

    public void restart(int width, int height, int snakeLength, int foodCount, int bombCount) {
        gameStatus = GameStatus.PLAYED;
        currentMoveStatus = MoveStatus.RIGHT;

        snakeBody = new SnakeBody(snakeLength, width, height);
        bombs = new Bomb(width, height, bombCount);
        foods = new Food(width, height, foodCount);

        this.snakeLength = snakeLength;

        this.width = width;
        this.height = height;
        this.bombCount = bombCount;
        this.foodCount = foodCount;


        bombs.fillData(snakeBody);
        foods.fillData(snakeBody, bombs);
    }


    public List<BodyCell> getSnakeBody() {
        return snakeBody.getSnakeList();
    }

    public List<Coord> getBombs() {
        return bombs.getWorkField();
    }

    public List<Coord> getFoods() {
        return foods.getWorkField();
    }

    public FieldObjectStatus getBombStatus() {
        return bombs.getFieldObjectStatus();
    }

    public FieldObjectStatus getFoodStatus() {
        return foods.getFieldObjectStatus();
    }


    public void move() {

        switch (currentMoveStatus) {
            case UP -> snakeBody.moveUp();
            case DOWN -> snakeBody.moveDown();
            case LEFT -> snakeBody.moveLeft();
            case RIGHT -> snakeBody.moveRight();
        }


        FieldObjectStatus headStatus = FieldObjectStatus.SNAKE_HEAD;

        if (!snakeBody.isInsideArea())
            gameStatus = GameStatus.OUT_OF_AREA;

        if (snakeBody.isBombed(bombs)) {
            gameStatus = GameStatus.BOMBED;
            headStatus = FieldObjectStatus.SNAKE_HEAD_BOMBED;
        }
//            bombed=true;


        if (snakeBody.isSelfEat()) {
            gameStatus = GameStatus.SELF_EATED;
            headStatus = FieldObjectStatus.SNAKE_HEAD_EATED;
        }

        if (snakeBody.isFindFood((foods))) {
            foods.removeCell(snakeBody.getNewHead());
            foods.generateGameObject(snakeBody, foods, bombs);
            snakeBody.increaseLength();
            snakeLength++;
            isEated = true;
            headStatus = FieldObjectStatus.SNAKE_HEAD_EATED;
        } else {
            snakeBody.moveEndOfSnake();
            isEated = false;
        }

        snakeBody.moveHeadOfSnake();
        snakeBody.setHeadStatus(headStatus);

    }

//    public void setCurrentMoveStatus(MoveStatus currentMoveStatus) {
//        this.currentMoveStatus = currentMoveStatus;
//    }


//    public GameStatus getGameStatus() {
//        return gameStatus;
//    }

//    public int getSnakeLength() {
//        return snakeBody.getSnakeLength();
//    }


}
