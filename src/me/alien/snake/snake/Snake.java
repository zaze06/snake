package me.alien.snake.snake;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import me.alien.snake.util.KeyMode;

public class Snake {

    private int size;
    private int lenth;

    SnakeNode head;

    public Snake(int lenth, int size) {
        this.lenth = lenth;
        //this.size = size;

        head = new SnakeNode(size, lenth, 0);
    }

    public boolean move(int key, Graphics2D g2d){
        if(key == KeyMode.UP){
            return head.move(head.getX(), head.getY()-head.getHeight(), g2d, true);
        }else if(key == KeyMode.DOWN){
            return head.move(head.getX(), head.getY()+head.getHeight(), g2d, true);
        }else if(key == KeyMode.LEFT){
            return head.move(head.getX()-head.getWidth(), head.getY(), g2d, true);
        }else if(key == KeyMode.RIGHT){
            return head.move(head.getX()+head.getWidth(), head.getY(), g2d, true);
        }
        System.out.println("Invalid key got "+key+" Expected betwen 1 to 4");
        return false;
    }

    public void add(){
        head.add();
    }

}
