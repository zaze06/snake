package me.alien.snake.util;

import me.alien.snake.Game;

import java.awt.*;

public class Wall extends Rectangle {
    int mode;
    static int count = 0;
    public Wall(int x, int y, int width, int height, int mode){
        super(x, y, width, height);
        //System.out.println("Cnt = "+count+"(x,y,w,h)=("+x+","+y+","+width+","+height+")");
        count++;
    }

    public boolean checkIntesection(Rectangle r){
        final boolean intersect = this.intersects(r);
        if(intersect){
            if(mode == Modes.Walls.DENGER){
                Game.gameState=Modes.GameStates.DIED;
            }
        }
        return  intersect;
    }

    public void setSize(int x, int y, int width, int height) {
        this.y = y;
        this.x = x;
        this.width=width;
        this.height=height;
    }
}
