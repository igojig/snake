package ru.igojig.snake.statuses;

import ru.igojig.snake.Coordinate;

public enum MoveStatus {

    UP {
        @Override
        public void move(Coordinate coordinate) {
            coordinate.setY(coordinate.getY()-1);
        }
    },
    DOWN {
        @Override
        public void move(Coordinate coordinate) {
            coordinate.setY(coordinate.getY()+1);
        }
    },
    LEFT {
        @Override
        public void move(Coordinate coordinate) {
            coordinate.setX(coordinate.getX()-1);
        }
    },
    RIGHT {
        @Override
        public void move(Coordinate coordinate) {
            coordinate.setX(coordinate.getX()+1);
        }
    };

    public abstract void move(Coordinate coordinate);
}
