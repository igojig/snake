package ru.igojig.snake.field;

import lombok.Getter;
import ru.igojig.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

@Getter
public enum Box {
    //    EMPTY(Color.BLACK),
    FOOD( "mouse"),
    BOMB("bomb"),
    SNAKE_HEAD("snake_head"),
    SNAKE_BODY("snake_body"),
    SNAKE_TAIL("snake_tail");

    private final Image icon;
//    private final Color color;

    Box( String iconName) {
//        this.color = color;
        icon = loadIcon(iconName);
    }

    private Image loadIcon(String iconName) {
        URL resource = getClass().getResource(String.format("/img/%s.png", iconName));
        if (resource == null) {
            throw new RuntimeException(String.format("Cant load image [/img/%s.png]", iconName));
        }
        ImageIcon icon = new ImageIcon(resource);
        return icon.getImage().getScaledInstance(Main.IMAGE_SIZE, Main.IMAGE_SIZE, Image.SCALE_AREA_AVERAGING);
    }
}


