package me.alien.snake.snake;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import me.alien.snake.Game;
import me.alien.snake.util.Data;
import sun.java2d.loops.ProcessPath;

import javax.swing.plaf.ColorUIResource;

public class SnakeNode {
    SnakeNode childNode=null;
    private int size;
    private int id;
    public Rectangle bodyPice;
    private Robot robot;
    private int oldDir;
    private int dir;
    private BufferedImage img;

    public SnakeNode(int size, int lenth, int id) {
        this.size=size;
        this.id=id;
        System.out.println("nodes left "+lenth+". This node has id "+id);
        bodyPice=new Rectangle(40, 40, size, size);
        if(lenth > 1){
            childNode = new SnakeNode(size, lenth-1, id+1);
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public boolean move(int x, int y, Graphics2D g2d, int dir, boolean isHead, boolean showID){

        this.oldDir = this.dir;

        if     (y>bodyPice.y) this.dir = Data.Key.DOWN;
        else if(y<bodyPice.y) this.dir = Data.Key.UP;
        else if(x>bodyPice.x) this.dir = Data.Key.RIGHT;
        else if(x<bodyPice.x) this.dir = Data.Key.LEFT;

        //System.out.println("Moving node whit id "+id);
        int gameWidth = Game.getWidthA();
        int gameHight = Game.getHightA();
        if(bodyPice == null) {
            System.out.println("bodyPice är NULL");
        }
        if(Game.border.intersect(bodyPice)){
            System.out.println("Invalid/eligel move trying to move node "+id+" out side of the map");
            return false;
        }
        int x1 = bodyPice.x, y1=bodyPice.y;

        bodyPice.setLocation(x, y);

        if(true){
            if(childNode!=null){
                System.out.println("id:"+id+", (childNode.dir!=this.dir)="+(childNode.dir!=this.dir));
            }

            System.out.println("id:"+id+", dir="+dir+", oldDir="+oldDir);
            String pice = Game.imgHandler.STRAIGHT;
            int imgDir = this.dir;
            if(childNode==null){
                pice = Game.imgHandler.TAIL;
            }else if(isHead){
                pice = Game.imgHandler.HEAD;
            }else if(oldDir!=this.dir){

                boolean down = (this.dir==Data.Key.DOWN);
                boolean left = (this.dir==Data.Key.LEFT);
                pice = Game.imgHandler.CORNER;

                if(down && left){
                    imgDir=1;
                }else if(!down && left){
                    imgDir=2;
                }else if(down){
                    imgDir=0;
                }else{
                    imgDir=3;
                }
            }

            img = Game.imgHandler.getImg(imgDir, pice);

            if(img == null) {
                System.out.println("img = null");
            }
            g2d.drawImage(img, null, x, y);


        }else if(isHead) {
            g2d.setColor(new ColorUIResource(40, 118, 29));
            g2d.fill(bodyPice);
            g2d.setColor(Game.snakeColor);
        } else g2d.fill(bodyPice);

        if(showID){
            g2d.setColor(Game.textColor);
            drawString(g2d,""+id, x, y-5, 10);
            g2d.setColor(Game.snakeColor);
        }


        if(isHead){

            if(childNode.intersect(bodyPice)){
                Game.gameState= Data.GameStates.DIED;
                System.out.println("Invalid/eligel move trying to move node "+id+" in to it the snake");
            }

            if(bodyPice.intersects(Game.appel.getAppel())){
                Game.appel.newAppel();
                Game.snake.add();
            }
        }
        if(childNode!=null){
            childNode.move(x1, y1, g2d, dir, false, showID);
        }else{
            //System.out.print("\n");
        }
        return true;
    }

    private boolean intersect(Rectangle r) {
        boolean intersected = this.bodyPice.intersects(r);
        if(intersected){
            return intersected;
        }
        if(childNode==null){
            return false;
        }
        return childNode.intersect(r);
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
            //childNode.childNode = new SnakeNode(size, 1, id+2);
        }
    }

    public boolean remove() {
        if(childNode == null){
            return false;
        }else{
            if(!childNode.remove()){
                childNode = null;
            }
        }
        return true;
    }
}
