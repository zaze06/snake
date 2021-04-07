package me.alien.snake.snake;

import java.awt.*;

import me.alien.snake.Game;

public class SnakeNode {

    SnakeNode childNode;
    private int size;
    private Rectangle bodyPice;

    public SnakeNode(int size, SnakeNode childNode) {
        this.childNode=childNode;
        this.size=size;
        bodyPice=new Rectangle(Game.getHightA()/2-5, Game.getWidthA()/2-5, size, size);
    }

    public void move(int x, int y){
        int x1 = bodyPice.x, y1=bodyPice.y;
        bodyPice.setLocation(x, y);
        childNode.move(x1, y1);
    }

    public int getHeight(){return bodyPice.height;}
    public int getWidth(){return bodyPice.width;}

    public int getX(){return bodyPice.x;}
    public int getY(){return bodyPice.y;}
}
