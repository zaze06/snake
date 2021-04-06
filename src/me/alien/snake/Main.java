package me.alien.snake;

import javax.swing.*;
import java.awt.*;
import me.alien.snake.*;

public class Main extends JFrame {

    static int hight = 270;
    static int width = 290;

    public Main(){
        initUI();
    }

    private void initUI(){
        Game game = new Game();
        add(game);
        addKeyListener(game);

        setTitle("Snake");
        setSize(width, hight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(getSize());
    }

    public static void main(String[] args){
        EventQueue.invokeLater((new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        }));
    }
}
