package ru.igojig;



import ru.igojig.snake.statuses.MoveStatus;
import ru.igojig.snake.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Window extends JFrame implements Runnable {

    Game game;
    JPanel panel;
    JPanel panelInfo;
    JLabel labelInfo;

    Timer timer;

    public Window() {
        game = new Game(Main.FIELD_WIDTH, Main.FIELD_HEIGHT, Main.SNAKE_START_LENGTH, Main.FOOD_COUNT, Main.BOMB_COUNT);
    }

    @Override
    public void run() {
        initPanel();
        initFrame();
        initTimer();
    }

    void restart() {
        game.restart(Main.FIELD_WIDTH, Main.FIELD_HEIGHT, Main.SNAKE_START_LENGTH, Main.FOOD_COUNT, Main.BOMB_COUNT);
        timer.setDelay(Main.TIMER_DELAY);
    }

    void initPanel() {
        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                game.getBombs().forEach(o -> {
                    int x = o.getX();
                    int y = o.getY();
                    g.drawImage(game.getBombStatus().getImage(), x* Main.IMAGE_SIZE, y*Main.IMAGE_SIZE, this);
                });

                game.getFoods().forEach(o -> {
                    int x = o.getX();
                    int y = o.getY();
                    g.drawImage(game.getFoodStatus().getImage(), x*Main.IMAGE_SIZE, y*Main.IMAGE_SIZE, this);
                });

                game.getSnake().forEach(o -> {
                    Image image = o.getFieldStatus().getImage();
                    int x = o.getCoordinate().getX();
                    int y = o.getCoordinate().getY();
                    g.drawImage(image, x*Main.IMAGE_SIZE, y*Main.IMAGE_SIZE, this);
                });

                drawGrid(g);

                labelInfo.setText("Length: " + game.getSnakeLength());
            }
        };

        panel.setPreferredSize(new Dimension(Main.FIELD_WIDTH * Main.IMAGE_SIZE, Main.FIELD_HEIGHT * Main.IMAGE_SIZE));

        panel.setBackground(Color.GRAY);
        add(panel);

        panelInfo = new JPanel(new FlowLayout());
        labelInfo = new JLabel();
        labelInfo.setText("Length: " + game.getSnakeLength());
        panelInfo.add(labelInfo);
        add(panelInfo, BorderLayout.SOUTH);
    }

    private static void drawGrid(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 1; i < Main.FIELD_WIDTH; i++) {
            g.drawLine((Main.IMAGE_SIZE * i), 0, Main.IMAGE_SIZE * i, Main.IMAGE_SIZE * Main.FIELD_HEIGHT);
        }
        for (int i = 1; i < Main.FIELD_HEIGHT; i++) {
            g.drawLine(0, Main.IMAGE_SIZE * i, Main.IMAGE_SIZE * Main.FIELD_WIDTH, Main.IMAGE_SIZE * i);
        }
    }

    void initTimer() {
        timer = new Timer(Main.TIMER_DELAY, e -> {

            game.move();

            repaint();
            int answer = -1;

            switch (game.getGameStatus()) {
                case BOMBED:
                case SELF_EATED:
                case OUT_OF_AREA:
                    answer = JOptionPane.showConfirmDialog(panel, game.getGameStatus().getStatus() + "\nНачать заново?", "Snake", JOptionPane.YES_NO_OPTION);

                    switch (answer) {
                        case JOptionPane.YES_OPTION:
                            restart();
                            break;
                        case JOptionPane.NO_OPTION:
                        default:
                            timer.stop();
                    }
                    break;
            }
        });
        timer.start();

    }

    void initFrame() {

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> game.setCurrentMoveStatus(MoveStatus.UP);
                    case KeyEvent.VK_DOWN -> game.setCurrentMoveStatus(MoveStatus.DOWN);
                    case KeyEvent.VK_LEFT -> game.setCurrentMoveStatus(MoveStatus.LEFT);
                    case KeyEvent.VK_RIGHT -> game.setCurrentMoveStatus(MoveStatus.RIGHT);
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
