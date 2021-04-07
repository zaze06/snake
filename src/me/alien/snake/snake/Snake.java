package me.alien.snake.snake;

import java.util.ArrayList;

public class Snake {

    private int size;
    private int lenth;

    ArrayList<SnakeNode> body = new ArrayList<SnakeNode>();

    public Snake(int lenth, int size) {
        this.lenth = lenth;
        //this.size = size;

        for(int i = 0; i < lenth; i++){
            /*if(i != 0){
                body.add(new SnakeNode(size, body.get(i-1)));
            }
            else{
                body.add(new SnakeNode(size, null));
            }*/
        }
    }

    public void move(){

    }

}
