package me.alien.snake;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import me.alien.snake.snake.Appel;
import me.alien.snake.snake.Snake;
import me.alien.snake.util.Modes;

import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements KeyListener, ActionListener {

    private static final long serialVersionUID = 1L;



    private static final String VERSION = "dev.0.2";
    public static Border border = null;
    public static int gameState = Modes.GameStates.RUNING;
    public static boolean showID;

    private static int hight = 0;
    private int DELAY = 100;
    private static int width;

    private static Timer timer;

    public static Snake snake = new Snake(3,10);
    public static Appel appel = null;

    public static final Color appelColor = new ColorUIResource(231, 42, 42);
    public static final Color backGroundColor = new ColorUIResource(97, 142, 114);
    public static final Color snakeColor = new ColorUIResource(67, 198, 50);
    public static final Color textColor = new ColorUIResource(23, 127, 106);

    private int pressedKey = Modes.Key.RIGHT;

    public static int getHightA(){return hight;}
    public static int getWidthA(){return width;}

    //the constructor
    public Game(int DELAY){
        this.DELAY = DELAY;
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
                //System.out.print("The size of the game was width:"+width+" height:"+hight);
                if(width % 10 == 0){}else{
                    width=Math.round(width/10)*10;
                }
                if(height % 10 == 0){}else{
                    height=Math.round(height/10)*10;
                }
                //System.out.print(" Now it is width:"+width+" height:"+hight+"\n");
                component.setSize(size);
                if(border == null){
                    border=new Border(width, height);
                }
                border.updateWalls(width, height);
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

    boolean setOrigo = false;

    private void doDrawing(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        if(!setOrigo){
            //g2d.scale(1, -1);
            g2d.translate(5, 5);
        }

        hight = getHeight();
        width = getWidth();

        if(appel == null){
            int x = (int) (Math.random() * (width + 1));
            int y = (int) (Math.random() * (hight + 1));
            appel = new Appel(x, y);
        }

        if(!(gameState == Modes.GameStates.DIED || gameState == Modes.GameStates.VICTORY)){
            g2d.setColor(backGroundColor);
            g2d.fillRect(-5, -5, width, hight);
        }


        if(gameState == Modes.GameStates.DIED){
            g2d.setColor(textColor);
            g2d.drawString("You Died.\nYou score: "+snake.getLenth(), hight/2, 40);
            border.drew(g2d);

            appel.draw(g2d, appelColor);

            g2d.setColor(snakeColor);

            snake.draw(g2d);
        }else if(gameState == Modes.GameStates.RUNING){
            border.drew(g2d);

            appel.draw(g2d, appelColor);

            g2d.setColor(snakeColor);

            if(!snake.move(pressedKey, g2d, showID)){
                timer.stop();
                repaint();
                return;
            }
        }else if(gameState == Modes.GameStates.NOT_STARTED){

        }else if(gameState == Modes.GameStates.VICTORY){
            g2d.setColor(textColor);
            g2d.drawString("YOU WON!\nYou score: "+snake.getLenth(), hight/2, 40);
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
        final int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP){
            pressedKey = Modes.Key.UP;
        }else
        if(keyCode == KeyEvent.VK_DOWN){
            pressedKey = Modes.Key.DOWN;
        }else
        if(keyCode == KeyEvent.VK_LEFT){
            pressedKey = Modes.Key.LEFT;
        }else
        if(keyCode == KeyEvent.VK_RIGHT){
            pressedKey = Modes.Key.RIGHT;
        }else
        if(keyCode == KeyEvent.VK_PLUS){
            snake.add();
        }else
        if(keyCode == KeyEvent.VK_MINUS){
            snake.add();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
