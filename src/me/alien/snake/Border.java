package me.alien.snake;

import me.alien.snake.util.Modes;
import me.alien.snake.util.Wall;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;

public class Border {
    int mode = Modes.Walls.DENGER;
    ArrayList<Wall> walls = new ArrayList<Wall>();
    Color[] wallColor = {
            new ColorUIResource(102, 5, 5),
            new ColorUIResource(151, 245, 26)
    };

    public Border(int width, int height){
        walls.add(new Wall(0, 0, width, 5, mode));
        walls.add(new Wall(0, 0, 5, height, mode));
        walls.add(new Wall(0, height-5, width, 5, mode));
        walls.add(new Wall(width-5, 0, 5, height, mode));
    }

    public void drew(Graphics2D g2d){
        g2d.setColor(wallColor[mode]);
        for(int i = 0; i < walls.size(); i++){
            g2d.draw(walls.get(i));
        }
    }

    public boolean intersect(Rectangle r){
        boolean[] test = new boolean[4];
        for(int i = 0; i < walls.size(); i++){
            test[i] = walls.get(i).checkIntesection(r);
        }
        return(test[0]||test[1]||test[2]||test[3]);
    }

    public void updateWalls(int width, int height){
        walls.get(0).setSize(0, 0, width, 5);
        walls.get(0).setSize(0, 0, 5, height);
        walls.get(0).setSize(0, height-5, width, 5);
        walls.get(0).setSize(width-5, 0, 5, height);
    }
}
