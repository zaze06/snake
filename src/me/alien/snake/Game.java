package me.alien.snake;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import me.alien.snake.snake.*;
import me.alien.snake.snake.Appel;
import me.alien.snake.snake.Snake;
import me.alien.snake.util.KeyMode;

import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements KeyListener, ActionListener {

    private static final long serialVersionUID = 1L;



    private static final String VERSION = "dev.0.2";

    private static int hight = 0;
    private int DELAY = 100;
    private static int width;

    private static Timer timer;

    public static Snake snake = new Snake(3,10);
    public static Appel appel = null;

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
        initTimer();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                final Component component = e.getComponent();
                Dimension size = component.getSize();
                int width = (int) size.getWidth();
                int height = (int) size.getHeight();
                System.out.print("The size of the game was width:"+width+" height:"+hight);
                if(width % 10 == 0){}else{
                    width=Math.round(width/10)*10;
                }
                if(height % 10 == 0){}else{
                    height=Math.round(width/10)*10;
                }
                System.out.print(" Now it is width:"+width+" height:"+hight+"\n");
                component.setSize(size);
            }
        });
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

        if(appel == null){
            int x = (int) (Math.random() * (width + 1));
            int y = (int) (Math.random() * (hight + 1));
            appel = new Appel(x, y);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(backGroundColor);
        g2d.fillRect(0, 0, width, hight);

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
