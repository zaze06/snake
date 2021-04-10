package me.alien.snake.snake;

import java.awt.*;

import apple.laf.JRSUIConstants;
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

    public boolean move(int x, int y, Graphics2D g2d, boolean isHead, boolean showID){
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
        g2d.setColor(Game.textColor);
        drawString(g2d,""+id, x, y-5, 10);
        g2d.setColor(Game.snakeColor);

        if(isHead){
            if(bodyPice.intersects(Game.appel.getAppel())){
                Game.appel.newAppel();
                Game.snake.add();
            }
        }
        if(childNode!=null){
            childNode.move(x1, y1, g2d, false, showID);
        }
        return true;
    }

    public void drawString(Graphics g, String s, int x, int y, int width)
    {
        // FontMetrics gives us information about the width,
        // height, etc. of the current Graphics object's Font.
        FontMetrics fm = g.getFontMetrics();

        int lineHeight = fm.getHeight();

        int curX = x;
        int curY = y;

        String[] words = s.split(" ");

        for (String word : words)
        {
            // Find out thw width of the word.
            int wordWidth = fm.stringWidth(word + " ");

            // If text exceeds the width, then move to next line.
            if (curX + wordWidth >= x + width)
            {
                curY += lineHeight;
                curX = x;
            }

            g.drawString(word, curX, curY);

            // Move over to the right for next word.
            curX += wordWidth;
        }
    }

    public void draw(Graphics2D g2d){
        g2d.fill(bodyPice);
        if(childNode!=null){
            childNode.draw(g2d);
        }
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
