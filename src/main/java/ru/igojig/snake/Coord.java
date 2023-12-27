package ru.igojig.snake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.igojig.Main;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Coord {
    protected int x;
    protected int y;

    private static List<Coord> gameField;

//    public Coord(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }

    public Coord(Coord coord) {
        this.x = coord.x;
        this.y = coord.y;
    }

    public static void initGameField() {
        for (int x = 0; x < Main.WIDTH; x++) {
            for (int y = 0; y < Main.HEIGHT; y++) {
                gameField.add(new Coord(x, y));
            }
        }
    }

    public static Coord getRandomCoord(int boundX, int boundY) {
        int x = ThreadLocalRandom.current().nextInt(boundX);
        int y = ThreadLocalRandom.current().nextInt(boundY);
        return getCoordByXY(x, y).orElseThrow();
    }

    public static Optional<Coord> getCoordByXY(int x, int y) {
        return gameField.stream()
                .filter(c -> c.getX() == x && c.getY() == y)
                .findFirst();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Coord coord)) return false;
//        return x == coord.x && y == coord.y;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(x, y);
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }

//    public Coord getCoord() {
//        return this;
//    }
}
