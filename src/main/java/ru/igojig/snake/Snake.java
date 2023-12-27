package ru.igojig.snake;



import ru.igojig.snake.body.BodyCell;
import ru.igojig.snake.body.HeadStatus;
import ru.igojig.snake.body.SnakeBody;
import ru.igojig.snake.field.Bomb;
import ru.igojig.snake.field.FieldStatus;
import ru.igojig.snake.field.Food;

import java.util.List;

public class Snake {
    MoveStatus currentMoveStatus;

    Bomb bombs;
    Food foods;
    SnakeBody snakeBody;

    GameStatus gameStatus;

    int snake_length;
    int max_x;
    int max_y;
    int foodCount;
    int bombCount;

    boolean isEated=false;


    public Snake(int max_x, int max_y, int snake_length, int foodCount, int bombCount) {


       restart(max_x, max_y, snake_length, foodCount, bombCount);

    }

    public void restart(int max_x, int max_y, int snake_length, int foodCount, int bombCount){
        gameStatus = GameStatus.PLAYED;
        currentMoveStatus = MoveStatus.RIGHT;

        snakeBody = new SnakeBody(snake_length, max_x, max_y);
        bombs = new Bomb(max_x, max_y, bombCount);
        foods = new Food(max_x, max_y, foodCount);

        this.snake_length = snake_length;

        this.max_x = max_x;
        this.max_y = max_y;
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

    public FieldStatus getBombStatus() {
        return bombs.getFieldStatus();
    }

    public FieldStatus getFoodStatus() {
        return foods.getFieldStatus();
    }

//    public List<Coord>[] getGameObjects() {
//        return new List[]{bombs.workField, foods.workField};
//    }

    public void move() {

        switch (currentMoveStatus) {
            case UP -> snakeBody.moveUp();
            case DOWN -> snakeBody.moveDown();
            case LEFT -> snakeBody.moveLeft();
            case RIGHT -> snakeBody.moveRight();
        }


//        Coord new_coord = snakeBody.getNewHead();

        HeadStatus headStatus = HeadStatus.NORMAL;

        if (!snakeBody.isInsideArea())
            gameStatus = GameStatus.OUT_OF_ARAE;

        if (snakeBody.isBombed(bombs)) {
            gameStatus = GameStatus.BOMBED;
            headStatus = HeadStatus.BOMBED;
        }
//            bombed=true;


        if (snakeBody.isSelfEat()) {
            gameStatus = GameStatus.SELF_EATED;
            headStatus = HeadStatus.SELF_EATED;
        }

        if (snakeBody.isFindFood((foods))) {
            foods.removeCell(snakeBody.getNewHead());
            foods.generateGameObject(snakeBody, foods, bombs);
            snakeBody.increaseLength();
            isEated=true;
            headStatus = HeadStatus.EATED;
        } else {
            snakeBody.moveEndOfSnake();
            isEated=false;
        }

        snakeBody.moveHeadOfSnake();
        snakeBody.setHeadStatus(headStatus);



//        lock.unlock();
    }

    public void setCurrentMoveStatus(MoveStatus currentMoveStatus) {
//        queue.addLast(this.currentMoveStatus);
        this.currentMoveStatus = currentMoveStatus;
    }


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getSnake_length() {
        return snakeBody.getSnakeLength();
    }

//    public List<BodyCell> getSnake(){
//         return snakeBody.getSnakeList();
//    }
public boolean isEated(){
        return isEated;
}
}
