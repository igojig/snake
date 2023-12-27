package ru.igojig.snake;



import ru.igojig.snake.body.BodyCell;
import ru.igojig.snake.body.HeadStatus;
import ru.igojig.snake.body.Snake;
import ru.igojig.snake.field.Bomb;
import ru.igojig.snake.field.Box;
import ru.igojig.snake.field.Food;

import java.util.List;

public class Game {
    MoveStatus currentMoveStatus;

    Bomb bombs;
    Food foods;
    Snake snake;

    GameStatus gameStatus;

    int snake_length;
    int max_x;
    int max_y;
    int foodCount;
    int bombCount;

    boolean isEated=false;


    public Game(int max_x, int max_y, int snake_length, int foodCount, int bombCount) {


       restart(max_x, max_y, snake_length, foodCount, bombCount);

    }

    public void restart(int max_x, int max_y, int snake_length, int foodCount, int bombCount){
        gameStatus = GameStatus.PLAYED;
        currentMoveStatus = MoveStatus.RIGHT;

        snake = new Snake(snake_length, max_x, max_y);
        bombs = new Bomb(max_x, max_y, bombCount);
        foods = new Food(max_x, max_y, foodCount);

        this.snake_length = snake_length;

        this.max_x = max_x;
        this.max_y = max_y;
        this.bombCount = bombCount;
        this.foodCount = foodCount;



        bombs.fillData(snake);
        foods.fillData(snake, bombs);
    }


    public List<BodyCell> getSnakeBody() {
        return snake.getSnakeList();
    }

    public List<Coord> getBombs() {
        return bombs.getWorkField();
    }

    public List<Coord> getFoods() {
        return foods.getWorkField();
    }

    public Box getBombStatus() {
        return bombs.getFieldStatus();
    }

    public Box getFoodStatus() {
        return foods.getFieldStatus();
    }

//    public List<Coord>[] getGameObjects() {
//        return new List[]{bombs.workField, foods.workField};
//    }

    public void move() {

        switch (currentMoveStatus) {
            case UP -> snake.moveUp();
            case DOWN -> snake.moveDown();
            case LEFT -> snake.moveLeft();
            case RIGHT -> snake.moveRight();
        }


//        Coord new_coord = snakeBody.getNewHead();

        HeadStatus headStatus = HeadStatus.NORMAL;

        if (!snake.isInsideArea())
            gameStatus = GameStatus.OUT_OF_AREA;

        if (snake.isBombed(bombs)) {
            gameStatus = GameStatus.BOMBED;
            headStatus = HeadStatus.BOMBED;
        }
//            bombed=true;


        if (snake.isSelfEat()) {
            gameStatus = GameStatus.SELF_EATED;
            headStatus = HeadStatus.SELF_EATED;
        }

        if (snake.isFindFood((foods))) {
            foods.removeCell(snake.getNewHead());
            foods.generateGameObject(snake, foods, bombs);
            snake.increaseLength();
            isEated=true;
            headStatus = HeadStatus.EATED;
        } else {
            snake.moveEndOfSnake();
            isEated=false;
        }

        snake.moveHeadOfSnake();
        snake.setHeadStatus(headStatus);



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
        return snake.getSnakeLength();
    }

//    public List<BodyCell> getSnake(){
//         return snakeBody.getSnakeList();
//    }
public boolean isEated(){
        return isEated;
}
}
