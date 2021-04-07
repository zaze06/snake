package me.alien.snake.snake;

import java.util.ArrayList;

import me.alien.snake.util.KeyMode;

public class Snake {

    private int size;
    private int lenth;

    ArrayList<SnakeNode> body = new ArrayList<SnakeNode>();

    public Snake(int lenth, int size) {
        this.lenth = lenth;
        //this.size = size;

        for(int i = 0; i < lenth; i++){
            
        }
    }

    public void move(int key){
        SnakeNode head = body.get(0);
        if(key == KeyMode.UP){
            head.move(head.getX(), head.getY()-head.getHeight());
        }else if(key == KeyMode.DOWN){
            head.move(head.getX(), head.getY()+head.getHeight());
        }else if(key == KeyMode.LEFT){
            head.move(head.getX()-head.getWidth(), head.getY());
        }else if(key == KeyMode.RIGHT){
            head.move(head.getX()+head.getWidth(), head.getY());
        }
    }

}
