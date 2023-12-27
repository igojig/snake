package ru.igojig;

import javax.swing.*;


public class Main {



    public static int X = 20;
    public static int Y = 20;
    public static int SIZE = 30;

    public static int FOOD_COUNT = 1;
    public static int BOMB_COUNT = 10;
    public static int SNAKE_LENGTH = 3;
    public static int DELAY = 500;




    public static void main(String[] args)  {

             SwingUtilities.invokeLater(new Window());
             
    }
}