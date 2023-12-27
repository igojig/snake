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
//        super();
        snake = new Snake(Main.X, Main.Y, Main.SNAKE_LENGTH, Main.FOOD_COUNT, Main.BOMB_COUNT);
    }

    @Override
    public void run() {
        initPanel();
        initFrame();
        initTimer();
    }

    void restart() {
        snake.restart(Main.X, Main.Y, Main.SNAKE_LENGTH, Main.FOOD_COUNT, Main.BOMB_COUNT);
        timer.setDelay(Main.DELAY);
    }

    void initPanel() {
        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Color c = snake.getBombStatus().getColor();
                g.setColor(c);
                snake.getBombs().forEach(o -> {

                    int x = o.getX();
                    int y = o.getY();

                    g.fillOval(x * Main.SIZE, y * Main.SIZE, Main.SIZE, Main.SIZE);
                });

                c = snake.getFoodStatus().getColor();
                g.setColor(c);
                snake.getFoods().forEach(o -> {

                    int x = o.getX();
                    int y = o.getY();

                    g.fillOval(x * Main.SIZE, y * Main.SIZE, Main.SIZE, Main.SIZE);
                });

//
//
//                com.company.com.company.com.company.snake.getArae().forEach(coordAreaStatusEntry -> {
//                    Color c = coordAreaStatusEntry.getValue().getColor();
//                    g.setColor(c);
//                    int x = coordAreaStatusEntry.getKey().getX();
//                    int y = coordAreaStatusEntry.getKey().getY();
//
//                    g.fillOval(x * com.company.Main.SIZE, y * com.company.Main.SIZE, com.company.Main.SIZE, com.company.Main.SIZE);
//
//                });


                snake.getSnakeBody().forEach(o -> {
                    Color color = o.getSegmentStatus().getColor();
//                    SegmentStatus statutus = segment.getSegmentStatus();
                    int x = o.getX();
                    int y = o.getY();

                    g.setColor(color);
                    g.fillRect(x * Main.SIZE, y * Main.SIZE, Main.SIZE, Main.SIZE);
                });

//
//                snake.getSnake().forEach(segment -> {
//                    Color c = segment.getSegmentStatus().getColor();
////                    SegmentStatus statutus = segment.getSegmentStatus();
//                    int x = segment.getCoord().getX();
//                    int y = segment.getCoord().getY();
//
//                    g.setColor(c);
//                    g.fillRect(x * Main.SIZE, y * Main.SIZE, Main.SIZE, Main.SIZE);
//
//                });

                g.setColor(Color.WHITE);
                for (int i = 1; i < Main.X; i++) {
                    g.drawLine((Main.SIZE * i), 0, Main.SIZE * i, Main.SIZE * Main.Y);
                }
                for (int i = 1; i < Main.Y; i++) {
                    g.drawLine(0, Main.SIZE * i, Main.SIZE * Main.X, Main.SIZE * i);
                }


//
            }

        };

        panel.setPreferredSize(new Dimension(Main.X * Main.SIZE, Main.Y * Main.SIZE));

        panel.setBackground(Color.GREEN);
        add(panel);

        panelInfo = new JPanel(new FlowLayout());
        labelInfo = new JLabel();
        labelInfo.setText("Length #" + snake.getSnake_length());
        panelInfo.add(labelInfo);
        add(panelInfo, BorderLayout.SOUTH);
    }

    void initTimer() {
        timer = new Timer(Main.DELAY, e -> {
//                System.out.println("timer");

            snake.move();

            Window.this.repaint();
            labelInfo.setText("Length #" + snake.getSnake_length());
            int answer = -1;

            switch (snake.getGameStatus()) {
                case BOMBED:
                case SELF_EATED:
                case OUT_OF_ARAE:
//                        JOptionPane.showMessageDialog(panel, snake.getGameStatus().getStatus());
                    answer = JOptionPane.showConfirmDialog(panel, snake.getGameStatus() + "\nНачать заново?", "Snake", JOptionPane.YES_NO_OPTION);

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

//                if (snake.isEated()) {
//                    timer.setDelay(timer.getDelay() - 10);
//                }
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
        //  setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}