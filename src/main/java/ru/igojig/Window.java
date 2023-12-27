package ru.igojig;



import ru.igojig.snake.MoveStatus;
import ru.igojig.snake.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Window extends JFrame implements Runnable {

    Snake snake;
    JPanel panel;
    JPanel panelInfo;
    JLabel labelInfo;

    Timer timer;

    public Window() {
        snake = new Snake(Main.FIELD_WIDTH, Main.FIELD_HEIGHT, Main.SNAKE_START_LENGTH, Main.FOOD_COUNT, Main.BOMB_COUNT);
    }

    @Override
    public void run() {
        initPanel();
        initFrame();
        initTimer();
    }

    void restart() {
        snake.restart(Main.FIELD_WIDTH, Main.FIELD_HEIGHT, Main.SNAKE_START_LENGTH, Main.FOOD_COUNT, Main.BOMB_COUNT);
        timer.setDelay(Main.TIMER_DELAY);
    }

    void initPanel() {
        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                snake.getBombs().forEach(o -> {
                    int x = o.getX();
                    int y = o.getY();
                    g.drawImage(snake.getBombStatus().getImage(), x* Main.IMAGE_SIZE, y*Main.IMAGE_SIZE, this);
                });

                snake.getFoods().forEach(o -> {
                    int x = o.getX();
                    int y = o.getY();
                    g.drawImage(snake.getFoodStatus().getImage(), x*Main.IMAGE_SIZE, y*Main.IMAGE_SIZE, this);
                });

                snake.getSnakeBody().forEach(o -> {
                    Image image = o.getFieldObjectStatus().getImage();
                    int x = o.getX();
                    int y = o.getY();
                    g.drawImage(image, x*Main.IMAGE_SIZE, y*Main.IMAGE_SIZE, this);
                });

                g.setColor(Color.WHITE);
                for (int i = 1; i < Main.FIELD_WIDTH; i++) {
                    g.drawLine((Main.IMAGE_SIZE * i), 0, Main.IMAGE_SIZE * i, Main.IMAGE_SIZE * Main.FIELD_HEIGHT);
                }
                for (int i = 1; i < Main.FIELD_HEIGHT; i++) {
                    g.drawLine(0, Main.IMAGE_SIZE * i, Main.IMAGE_SIZE * Main.FIELD_WIDTH, Main.IMAGE_SIZE * i);
                }

                labelInfo.setText("Length #" + snake.getSnakeLength());
            }
        };

        panel.setPreferredSize(new Dimension(Main.FIELD_WIDTH * Main.IMAGE_SIZE, Main.FIELD_HEIGHT * Main.IMAGE_SIZE));

        panel.setBackground(Color.GRAY);
        add(panel);

        panelInfo = new JPanel(new FlowLayout());
        labelInfo = new JLabel();
        labelInfo.setText("Length #" + snake.getSnakeLength());
        panelInfo.add(labelInfo);
        add(panelInfo, BorderLayout.SOUTH);
    }

    void initTimer() {
        timer = new Timer(Main.TIMER_DELAY, e -> {

            snake.move();

            Window.this.repaint();
            labelInfo.setText("Length #" + snake.getSnakeLength());
            int answer = -1;

            switch (snake.getGameStatus()) {
                case BOMBED:
                case SELF_EATED:
                case OUT_OF_AREA:
                    answer = JOptionPane.showConfirmDialog(panel, snake.getGameStatus().getStatus() + "\nНачать заново?", "Snake", JOptionPane.YES_NO_OPTION);

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
                    case KeyEvent.VK_UP:
                        snake.setCurrentMoveStatus(MoveStatus.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.setCurrentMoveStatus(MoveStatus.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        snake.setCurrentMoveStatus(MoveStatus.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.setCurrentMoveStatus(MoveStatus.RIGHT);
                        break;

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
