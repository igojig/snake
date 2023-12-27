package ru.igojig.snake.body;



import ru.igojig.snake.Coord;
import ru.igojig.snake.field.FieldObject;

import java.util.LinkedList;
import java.util.List;


public class SnakeBody {

    int max_x_size;
    int max_y_size;

    int snake_length;

    int new_x;
    int new_y;

    LinkedList<BodyCell> snake_list;

    public SnakeBody(int snake_length, int max_x_size, int max_y_size) {

        restart(snake_length, max_x_size, max_y_size);
    }

    public void restart(int snake_length, int max_x_size, int max_y_size){
        this.max_x_size=max_x_size;
        this.max_y_size=max_y_size;
        this.snake_length=snake_length;
        snake_list = new LinkedList<>();
        generateSnake();
    }


    public void generateSnake() {
        int y = max_y_size / 2;
        int x = max_x_size / 2 - snake_length / 2;

        for (int i = 0; i < snake_length; i++) {
            snake_list.add(new BodyCell(x + i, y, SegmentStatus.SNAKE_BODY));
        }
        snake_list.getLast().segmentStatus = SegmentStatus.SNAKE_HEAD;
        snake_list.getFirst().segmentStatus = SegmentStatus.SNAKE_END;


        new_y = y;
        new_x = x + snake_length - 1;
    }

    public boolean isEmpty(int x, int y) {
        return snake_list.stream().noneMatch(o -> o.getX() == x && o.getY() == y);
    }


    public void moveUp() {
        new_y--;
    }

    public void moveDown() {
        new_y++;
    }

    public void moveLeft() {
        new_x--;
    }

    public void moveRight() {
        new_x++;
    }


    public void moveEndOfSnake() {
        snake_list.remove();

    }

    public void moveHeadOfSnake() {
        snake_list.getLast().segmentStatus = SegmentStatus.SNAKE_BODY;
        snake_list.add(new BodyCell(new_x, new_y, SegmentStatus.SNAKE_HEAD));
        snake_list.element().segmentStatus = SegmentStatus.SNAKE_END;

    }

    public Coord getNewHead() {
        return new Coord(new_x, new_y);
    }

    public boolean isInsideArea() {
        return new_x >= 0 && new_x < max_x_size && new_y >= 0 && new_y < max_y_size;
    }

    public boolean isBombed(FieldObject fieldObject) {
        return !fieldObject.isEmpty(new_x, new_y);
    }

    public boolean isSelfEat() {
        return snake_list.stream().anyMatch(o -> o.getX() == new_x && o.getY() == new_y);
    }

    public boolean isFindFood(FieldObject fieldObject) {

        return !fieldObject.isEmpty(new_x, new_y);

    }

    public void increaseLength() {
        snake_length++;
    }

    public List<BodyCell> getSnakeList() {
        return List.copyOf(snake_list);
    }

    public int getSnakeLength() {
        return snake_length;
    }

    public void setHeadStatus(HeadStatus headStatus) {
        snake_list.getLast().segmentStatus.setHeadStatus(headStatus);
    }

}
