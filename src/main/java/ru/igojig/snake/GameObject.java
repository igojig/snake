package ru.igojig.snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public interface GameObject {
    List<Coordinate> getCoordinates();

    default List<Coordinate> generateObject(int count, List<Coordinate>... others) {
        List<Coordinate> randomCoordinates = new ArrayList<>();
        int i = 0;
        while (i < count) {
            int x = ThreadLocalRandom.current().nextInt(Game.width);
            int y = ThreadLocalRandom.current().nextInt(Game.height);

            if (Arrays.stream(others)
                    .flatMap(Collection::stream)
                    .noneMatch(c -> c.getX() == x && c.getY() == y)) {
                randomCoordinates.add(new Coordinate(x, y));
                i++;
            }
        }
        return randomCoordinates;
    }
}
