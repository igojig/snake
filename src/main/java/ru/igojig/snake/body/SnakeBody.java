package ru.igojig.snake.body;



import ru.igojig.snake.Coord;
import ru.igojig.snake.field.FieldObject;
import ru.igojig.snake.field.FieldStatus;

import java.util.LinkedList;
import java.util.List;


public class SnakeBody {

    int width;
    int height;

    int snakeLength;

    int currentX;
    int currentY;

    LinkedList<BodyCell> snakeList;

    public SnakeBody(int snakeLength, int width, int height) {

        restart(snakeLength, width, height);
    }

    public void restart(int snakeLength, int width, int height){
        this.width =width;
        this.height =height;
        this.snakeLength =snakeLength;
        snakeList = new LinkedList<>();
        generateSnake();
    }


    public void generateSnake() {
        int y = height / 2;
        int x = width / 2 - snakeLength / 2;

        for (int i = 0; i < snakeLength; i++) {
            snakeList.add(new BodyCell(x + i, y, FieldStatus.SNAKE_BODY));
        }
        snakeList.getLast().fieldStatus = FieldStatus.SNAKE_HEAD;
        snakeList.getFirst().fieldStatus = FieldStatus.SNAKE_TAIL;


        currentY = y;
        currentX = x + snakeLength - 1;
    }

    public boolean isEmpty(int x, int y) {
        return snakeList.stream().noneMatch(o -> o.getX() == x && o.getY() == y);
    }


    public void moveUp() {
        currentY--;
    }

    public void moveDown() {
        currentY++;
    }

    public void moveLeft() {
        currentX--;
    }

    public void moveRight() {
        currentX++;
    }


    public void moveEndOfSnake() {
        snakeList.remove();

    }

    public void moveHeadOfSnake() {
        snakeList.getLast().fieldStatus = FieldStatus.SNAKE_BODY;
        snakeList.add(new BodyCell(currentX, currentY, FieldStatus.SNAKE_HEAD));
        snakeList.element().fieldStatus = FieldStatus.SNAKE_TAIL;

    }

    public Coord getNewHead() {
        return new Coord(currentX, currentY);
    }

    public boolean isInsideArea() {
        return currentX >= 0 && currentX < width && currentY >= 0 && currentY < height;
    }

    public boolean isBombed(FieldObject fieldObject) {
        return !fieldObject.isEmpty(currentX, currentY);
    }

    public boolean isSelfEat() {
        return snakeList.stream().anyMatch(o -> o.getX() == currentX && o.getY() == currentY);
    }

    public boolean isFindFood(FieldObject fieldObject) {

        return !fieldObject.isEmpty(currentX, currentY);

    }

    public void increaseLength() {
        snakeLength++;
    }

    public List<BodyCell> getSnakeList() {
        return List.copyOf(snakeList);
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public void setHeadStatus(FieldStatus fieldStatus) {
        snakeList.getLast().setFieldStatus(fieldStatus);
    }

}
