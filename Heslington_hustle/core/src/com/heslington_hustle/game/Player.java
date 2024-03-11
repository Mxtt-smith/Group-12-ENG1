package com.heslington_hustle.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.awt.*;

public class Player extends Sprite {
    Rectangle playerShape;
    TextureAtlas textureAtlas;
    Sprite playerSprite;
    public static String character;
    private final TiledMapTileLayer collisionLayer;
    String direction;

    public Player(TextureAtlas atlas, TiledMapTileLayer gameCollisionLayer) {
        super(atlas.getRegions().get(0));
        direction = "DOWN";
        this.collisionLayer = gameCollisionLayer;
        textureAtlas = atlas;
        playerShape = new Rectangle(16, 16);
        playerSprite = new Sprite(textureAtlas.findRegion(character + "sd"));
    }

    public void setTexture(String textureName) {
        playerSprite.setRegion(textureAtlas.findRegion(textureName));
    }

    public boolean collision(int transX, int transY) {
        float oldX = playerSprite.getX();
        float oldY = playerSprite.getY();
        System.out.println("Current x:" + oldX);
        System.out.println("Current y:" + oldY);


        // get the collision tile for the corresponding future coordinate
        // Catch the null exception - no tile exists there
        try {
            // convert the coordinates into cell number, bottom left is 0, 0
            // map is 50 x 50 cells
            int cellX = (int) (oldX + transX) / 16;
            int cellY = (int) (oldY + transY) / 16;
            System.out.println("Cell: " + cellX + "," + cellY);
            TiledMapTileLayer.Cell cell = collisionLayer.getCell(cellX, cellY);
            return cell.getTile().getProperties().containsKey("Collidable");
        } catch (Exception e) {
            return false;
        }
    }

    // Movement methods
    public void moveUp() {
        playerSprite.translateY(2);
        this.setTexture(character+"u");
        this.direction = "UP";
    }
    public void moveDown() {
        playerSprite.translateY(-2);
        this.setTexture(character+"d");
        this.direction = "DOWN";
    }

    public void moveLeft() {
        playerSprite.translateX(-2);
        this.setTexture(character+"l");
        this.direction = "LEFT";
    }

    public void moveRight() {
        playerSprite.translateX(2);
        this.setTexture(character+"r");
        this.direction = "RIGHT";
    }

    public void setPosition(float x, float y) {
        playerSprite.setPosition(x, y);
    }

    public void stationary() {
        switch (this.direction) {
            case "DOWN":
                this.setTexture(character+"sd");
                break;
            case "UP":
                this.setTexture(character+"su");
                break;
            case "LEFT":
                this.setTexture(character+"sl");
                break;
            case "RIGHT":
                this.setTexture(character+"sr");
                break;
        }
    }

    @Override
    public void draw(Batch batch) {
        playerSprite.draw(batch);
    }
}