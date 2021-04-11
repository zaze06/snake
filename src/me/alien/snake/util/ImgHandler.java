package me.alien.snake.util;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImgHandler {

    public final String HEAD = "head";
    public final String TAIL = "tail";
    public final String STRAIGHT = "straight";
    public final String CORNER = "corner";

    public BufferedImage getImg(int key, String img){

        BufferedImage image = null;
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("/snake/"+img+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final double rads = Math.toRadians(rotate(key));
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);

        image=rotatedImage;

        return image;
    }

    private int rotate(int key) {
        return (90*key);
    }
}
