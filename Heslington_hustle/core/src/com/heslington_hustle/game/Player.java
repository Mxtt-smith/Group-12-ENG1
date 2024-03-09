package com.heslington_hustle.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.awt.*;

public class Player extends Sprite{
    Rectangle playerShape;
    TextureAtlas textureAtlas;
    Sprite playerSprite;
    String character;
    String direction;

    public Player(TextureAtlas atlas) {
        super(atlas.getRegions().get(0));
        character = "char1";
        direction = "DOWN";
        textureAtlas = atlas;
        playerShape = new Rectangle(16, 16);
        playerSprite = new Sprite(textureAtlas.findRegion("char1sd"));
    }

    public void setTexture(String textureName) {
        setRegion(textureAtlas.findRegion(textureName));
    }

    // Movement methods
    public void moveUp() {
        this.translateY(2);
        this.setTexture(character+"u");
        this.direction = "UP";
    }
    public void moveDown() {
        this.translateY(-2);
        this.setTexture(character+"d");
        this.direction = "DOWN";
    }

    public void moveLeft() {
        this.translateX(-2);
        this.setTexture(character+"l");
        this.direction = "LEFT";
    }

    public void moveRight() {
        this.translateX(2);
        this.setTexture(character+"r");
        this.direction = "RIGHT";
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
}