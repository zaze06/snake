package me.alien.snake;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import me.alien.*;
import me.alien.snake.snake.Appel;
import me.alien.snake.snake.Snake;
import me.alien.snake.util.KeyMode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String VERSION = "dev.0.2";

    private static int hight = 0;
    private int DELAY = 100;
    private static int width;

    private static Timer timer;

    Snake snake = new Snake(3,10);
    Appel appel;

    Color appelColor = new ColorUIResource(231, 42, 42);
    Color backGroundColor = new ColorUIResource(97, 142, 114);
    Color snakeColor = new ColorUIResource(67, 198, 50);

    private int pressedKey = KeyMode.RIGHT;

    public static int getHightA(){return hight;}
    public static int getWidthA(){return width;}

    //the constructor
    public Game(){
        System.out.println("Starting snake by Alien (c) 2021 V."+VERSION);
        hight=getHeight();
        width = getWidth();
        int x = (int) (Math.random() * (width + 1));
        int y = (int) (Math.random() * (hight + 1));
        appel = new Appel(x, y);
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g){
        hight = getHeight();
        width = getWidth();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(backGroundColor);

        appel.draw(g2d, appelColor);

        g2d.setColor(snakeColor);

        if(!snake.move(pressedKey, g2d)){
            timer.stop();
            return;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            pressedKey = KeyMode.UP;
        }else
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            pressedKey = KeyMode.DOWN;
        }else
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            pressedKey = KeyMode.LEFT;
        }else
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            pressedKey = KeyMode.RIGHT;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
