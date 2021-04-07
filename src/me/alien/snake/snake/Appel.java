package me.alien.snake.snake;

import me.alien.snake.Game;

import java.awt.*;

public class Appel {

    private final Rectangle appel;

    public Appel(int x, int y){
        appel = new Rectangle(x, y, 8, 8);
    }

    public void draw(Graphics2D g2d, Color appelColor){
        g2d.setColor(appelColor);
        g2d.fill(appel);
    }

    public Rectangle getAppel() {
        return appel;
    }

    public void newAppel(){
        int x = (int) (Math.random() * (Game.getWidthA() + 1));
        int y = (int) (Math.random() * (Game.getHightA() + 1));
        appel.setLocation(x, y);
    }
}
