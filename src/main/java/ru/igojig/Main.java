package ru.igojig;

import javax.swing.*;


public class Main {

    public static int FIELD_WIDTH = 15;
    public static int FIELD_HEIGHT = 15;
    public static int IMAGE_SIZE = 50;

    public static int FOOD_COUNT = 1;
    public static int BOMB_COUNT = 10;
    public static int SNAKE_START_LENGTH = 4;
    public static int TIMER_DELAY = 500;


    public static void main(String[] args)  {
             SwingUtilities.invokeLater(new Window());
    }
}