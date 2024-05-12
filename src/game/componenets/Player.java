package game.componenets;

import game.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private int posX;
    private int posY;

    public Player(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void move(Direction d, int speed){
        switch (d){
            case UP -> updateY(-speed);
            case DOWN ->  updateY(speed);
            case LEFT -> updateX(-speed);
            case RIGHT -> updateX(speed);
        }
    }

    public void updateX(int value){
        this.posX += value;
    }

    public void updateY(int value){
        this.posY += value;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void drawPlayer(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(posX,posY,50,50);
    }


}
