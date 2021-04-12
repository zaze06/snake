package me.alien.snake.snake;

import java.awt.Graphics2D;

import me.alien.snake.Game;
import me.alien.snake.util.Data;

public class Snake {

    private int size;
    private int lenth;

    SnakeNode head;

    public Snake(int lenth, int size) {
        this.lenth = lenth;
        //this.size = size;

        head = new SnakeNode(size, lenth, 0);
    }

    public SnakeNode getHead() {
        return head;
    }

    public boolean move(int key, Graphics2D g2d, boolean showID){
        if(lenth<=2){
            Game.gameState=Data.GameStates.DIED;
            return false;
        }
        if(key == Data.Key.UP){
            return head.move(head.getX(), head.getY()-head.getHeight(), g2d, key, true, showID);
        }else if(key == Data.Key.DOWN){
            return head.move(head.getX(), head.getY()+head.getHeight(), g2d, key, true, showID);
        }else if(key == Data.Key.LEFT){
            return head.move(head.getX()-head.getWidth(), head.getY(), g2d, key, true, showID);
        }else if(key == Data.Key.RIGHT){
            return head.move(head.getX()+head.getWidth(), head.getY(), g2d, key, true, showID);
        }
        System.out.println("Invalid key got "+key+" Expected betwen 1 to 4");
        return false;
    }

    public void add(){
        lenth++;
        head.add();
        if(lenth==((Game.getHightA()/10)*(Game.getWidthA()/10))){
            Game.gameState= Data.GameStates.VICTORY;
        }
    }
    public void remove(){
        lenth--;
        head.remove();
    }

    public void draw(Graphics2D g2d){
        head.draw(g2d);
    }

    public int getLenth() {
        return lenth;
    }
}
