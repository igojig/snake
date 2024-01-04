package ru.igojig.snake.statuses;

import lombok.Getter;
import ru.igojig.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

@Getter
public enum FieldStatus
{

    FOOD("food"),
    BOMB("bomb"),
    SNAKE_BODY("snake-body"),
    SNAKE_HEAD("snake-head"),
    SNAKE_TAIL("snake-tail"),
    SNAKE_HEAD_EATED("snake-head-eated"),
    SNAKE_HEAD_BOMBED("snake-head-bombed");

    private Image image;
    private final String iconNameFormat="/img/%s.png";

    FieldStatus(String iconName){
        loadImage(iconName);
    }

    private void loadImage(String iconName){
        URL resource = getClass().getResource(String.format(iconNameFormat, iconName));
        if(resource==null){
            throw new RuntimeException();
        }
        image=new ImageIcon(resource).getImage().getScaledInstance(Main.IMAGE_SIZE, Main.IMAGE_SIZE, Image.SCALE_AREA_AVERAGING);
    }

}
