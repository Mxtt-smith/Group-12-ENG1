package com.heslington_hustle.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.awt.*;

public class Player extends Sprite{
    Rectangle playerShape;
    TextureAtlas textureAtlas;
    Sprite playerSprite;

    public Player(TextureAtlas atlas) {
        super(atlas.getRegions().get(0));
        textureAtlas = atlas;
        playerShape = new Rectangle(16, 16);
        playerSprite = new Sprite(textureAtlas.findRegion("char1sd"));
    }

    public void setTexture(String textureName) {
        setRegion(textureAtlas.findRegion(textureName));
    }
}
