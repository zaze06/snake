package me.alien.snake.snake;

import java.awt.*;

import me.alien.snake.Border;
import me.alien.snake.Game;

public class SnakeNode {

    SnakeNode childNode=null;
    private int size;
    private int id;
    private Rectangle bodyPice;

    public SnakeNode(int size, int lenth, int id) {
        this.size=size;
        this.id=id;
        System.out.println("nodes left "+lenth+". This node has id "+id);
        bodyPice=new Rectangle(40, 40, size, size);
        if(lenth > 1){
            childNode = new SnakeNode(size, lenth-1, id+1);
        }
    }

    public boolean move(int x, int y, Graphics2D g2d, boolean isHead){
        //System.out.println("Moving node whit id "+id);
        int gameWidth = Game.getWidthA();
        int gameHight = Game.getHightA();
        if(Game.border.intersect(bodyPice)){
            System.out.println("Invalid/eligel move trying to move node "+id+" out side of the map");
            return false;
        }
        int x1 = bodyPice.x, y1=bodyPice.y;
        bodyPice.setLocation(x, y);
        g2d.fill(bodyPice);

        if(isHead){
            if(bodyPice.intersects(Game.appel.getAppel())){
                Game.appel.newAppel();
                Game.snake.add();
            }
        }
        if(childNode!=null){
            childNode.move(x1, y1, g2d, false);
        }
        return true;
    }

    public int getHeight(){return bodyPice.height;}
    public int getWidth(){return bodyPice.width;}

    public int getX(){return bodyPice.x;}
    public int getY(){return bodyPice.y;}

    public int getID() {
        return id;
    }

    public void add() {
        if (childNode != null) {
            childNode.add();
        } else {
            childNode = new SnakeNode(size, 1, id+1);
        }
    }
}
