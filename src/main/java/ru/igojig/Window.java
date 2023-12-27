package ru.igojig;



import ru.igojig.snake.MoveStatus;
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
//        super();
        game = new Game(Main.WIDTH, Main.HEIGHT, Main.SNAKE_LENGTH, Main.FOOD_COUNT, Main.BOMB_COUNT);
    }

    @Override
    public void run() {
        initPanel();
        initFrame();
        initTimer();
    }

    void restart() {
        game.restart(Main.WIDTH, Main.HEIGHT, Main.SNAKE_LENGTH, Main.FOOD_COUNT, Main.BOMB_COUNT);
        timer.setDelay(Main.DELAY);
    }

    void initPanel() {
        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Color c = game.getBombStatus().getColor();
                g.setColor(c);
                game.getBombs().forEach(o -> {

                    int x = o.getX();
                    int y = o.getY();

                    g.fillOval(x * Main.IMAGE_SIZE, y * Main.IMAGE_SIZE, Main.IMAGE_SIZE, Main.IMAGE_SIZE);
                });

                c = game.getFoodStatus().getColor();
                g.setColor(c);
                game.getFoods().forEach(o -> {

                    int x = o.getX();
                    int y = o.getY();

                    g.fillOval(x * Main.IMAGE_SIZE, y * Main.IMAGE_SIZE, Main.IMAGE_SIZE, Main.IMAGE_SIZE);
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


                game.getSnakeBody().forEach(o -> {
                    Color color = o.getSegmentStatus().getColor();
//                    SegmentStatus statutus = segment.getSegmentStatus();
                    int x = o.getX();
                    int y = o.getY();

                    g.setColor(color);
                    g.fillRect(x * Main.IMAGE_SIZE, y * Main.IMAGE_SIZE, Main.IMAGE_SIZE, Main.IMAGE_SIZE);
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
                for (int i = 1; i < Main.WIDTH; i++) {
                    g.drawLine((Main.IMAGE_SIZE * i), 0, Main.IMAGE_SIZE * i, Main.IMAGE_SIZE * Main.HEIGHT);
                }
                for (int i = 1; i < Main.HEIGHT; i++) {
                    g.drawLine(0, Main.IMAGE_SIZE * i, Main.IMAGE_SIZE * Main.WIDTH, Main.IMAGE_SIZE * i);
                }


//
            }

        };

        panel.setPreferredSize(new Dimension(Main.WIDTH * Main.IMAGE_SIZE, Main.HEIGHT * Main.IMAGE_SIZE));

        panel.setBackground(Color.GREEN);
        add(panel);

        panelInfo = new JPanel(new FlowLayout());
        labelInfo = new JLabel();
        labelInfo.setText("Length #" + game.getSnake_length());
        panelInfo.add(labelInfo);
        add(panelInfo, BorderLayout.SOUTH);
    }

    void initTimer() {
        timer = new Timer(Main.DELAY, e -> {
//                System.out.println("timer");

            game.move();

            Window.this.repaint();
            labelInfo.setText("Length #" + game.getSnake_length());
            int answer = -1;

            switch (game.getGameStatus()) {
                case BOMBED:
                case SELF_EATED:
                case OUT_OF_AREA:
//                        JOptionPane.showMessageDialog(panel, snake.getGameStatus().getStatus());
                    answer = JOptionPane.showConfirmDialog(panel, game.getGameStatus() + "\nНачать заново?", "Snake", JOptionPane.YES_NO_OPTION);

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
                        game.setCurrentMoveStatus(MoveStatus.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.setCurrentMoveStatus(MoveStatus.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        game.setCurrentMoveStatus(MoveStatus.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.setCurrentMoveStatus(MoveStatus.RIGHT);
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
