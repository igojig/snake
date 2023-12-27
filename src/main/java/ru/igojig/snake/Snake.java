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

    int snakeLength;
    int width;
    int height;
    int foodCount;
    int bombCount;

    boolean isEated=false;


    public Snake(int width, int height, int snakeLength, int foodCount, int bombCount) {


       restart(width, height, snakeLength, foodCount, bombCount);

    }

    public void restart(int width, int height, int snakeLength, int foodCount, int bombCount){
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

        FieldStatus head = FieldStatus.SNAKE_HEAD;

        if (!snakeBody.isInsideArea())
            gameStatus = GameStatus.OUT_OF_AREA;

        if (snakeBody.isBombed(bombs)) {
            gameStatus = GameStatus.BOMBED;
            head = FieldStatus.SNAKE_HEAD_BOMBED;
        }
//            bombed=true;


        if (snakeBody.isSelfEat()) {
            gameStatus = GameStatus.SELF_EATED;
            head = FieldStatus.SNAKE_HEAD_EATED;
        }

        if (snakeBody.isFindFood((foods))) {
            foods.removeCell(snakeBody.getNewHead());
            foods.generateGameObject(snakeBody, foods, bombs);
            snakeBody.increaseLength();
            isEated=true;
            head = FieldStatus.SNAKE_HEAD_EATED;
        } else {
            snakeBody.moveEndOfSnake();
            isEated=false;
        }

        snakeBody.moveHeadOfSnake();
        snakeBody.setHeadStatus(head);



//        lock.unlock();
    }

    public void setCurrentMoveStatus(MoveStatus currentMoveStatus) {
//        queue.addLast(this.currentMoveStatus);
        this.currentMoveStatus = currentMoveStatus;
    }


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getSnakeLength() {
        return snakeBody.getSnakeLength();
    }

//    public List<BodyCell> getSnake(){
//         return snakeBody.getSnakeList();
//    }
public boolean isEated(){
        return isEated;
}
}
