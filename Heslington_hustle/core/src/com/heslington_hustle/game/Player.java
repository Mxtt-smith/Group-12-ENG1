package com.heslington_hustle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Sprite {
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
        playerSprite = new Sprite(textureAtlas.findRegion(character + "sd"));
        playerSprite.setOriginCenter();

    }

    public void setTexture(String textureName) {
        playerSprite.setRegion(textureAtlas.findRegion(textureName));
    }

    public boolean collision(int transX, int transY) {
        float oldX = playerSprite.getX();
        float oldY = playerSprite.getY();
        System.out.println("Current x:" + oldX);
        System.out.println("Current y:" + oldY);

        float cellX = (oldX + transX) / 16;
        float cellY = (oldY + transY) / 16;
        TiledMapTileLayer.Cell cell;
        // Check upper bound collision
        // convert the coordinates into cell number, bottom left is 0, 0
        // map is 50 x 50 cells
        try {
            cell = collisionLayer.getCell((int) Math.ceil(cellX), (int) Math.ceil(cellY));
            System.out.println("Checking cell: " + (int) Math.ceil(cellX)+','+ (int) Math.ceil(cellY));
            // try upper bound
            if (cell.getTile().getProperties().containsKey("Collidable")) {
                System.out.println("The upper bound tile is collidable");
                return true;
            }
        } catch (Exception e) {
            // Catch the null exception - no tile exists there
            System.out.println("No tile to collide into at upper bound");
        }

        // Check for collision for lower bound
        try {
            cell = collisionLayer.getCell((int) Math.floor(cellX), (int) Math.floor(cellY));
            System.out.println("Checking cell: " + (int) Math.floor(cellX) + ',' + (int) Math.floor(cellY));

            if (cell.getTile().getProperties().containsKey("Collidable")) {
                System.out.println("The lower bound tile is collidable");
                return true;
            }
        } catch (Exception e) {
            // Catch the null exception - no tile exists there
            System.out.println("No tile to collide into at lower bound");
        }

        // Create player rectangle
        final Rectangle bounds = playerSprite.getBoundingRectangle();

        // Create screen rectangle
        final Rectangle screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Set the values of the player rectangle
        float left = bounds.getX();
        float bottom = bounds.getY();
        float top = bottom + bounds.getHeight();
        float right = left + bounds.getWidth();


        // Set the values of the screen rectangle
        float screenLeft = (float) screenBounds.getX();
        float screenBottom = (float) screenBounds.getY();
        float screenTop = (float) (screenBottom + screenBounds.getHeight());
        float screenRight = (float) (screenLeft + screenBounds.getWidth());

        // Get player position
        float correctX = playerSprite.getX();
        float correctY = playerSprite.getY();

        // Screen sides
        if(left < screenLeft)
        {
            correctX = screenLeft;
        }
        else if(right > screenRight)
        {
            correctX = screenRight - 16;
        }

        // Top and bottom
        if(bottom < screenBottom)
        {
            correctY = screenBottom;
        }
        else if(top > screenTop)
        {
            correctY = screenTop - 16;
        }

        // Set player position
        playerSprite.setPosition(correctX, correctY);

        // No collision at either bound, so there is no tile
        return false;
    }

    // Movement methods
    public void moveUp() {
        if (playerSprite.getY() + 2 < 800) {
            playerSprite.translateY(2);
            this.setTexture(character + "u");
            this.direction = "UP";
        }
    }
    public void moveDown() {
        if (playerSprite.getY() - 2 > 0) {
            playerSprite.translateY(-2);
            this.setTexture(character + "d");
            this.direction = "DOWN";
        }
    }

    public void moveLeft() {
        if (playerSprite.getX() - 2 > 0) {
            playerSprite.translateX(-2);
            this.setTexture(character + "l");
            this.direction = "LEFT";
        }
    }

    public void moveRight() {
        if (playerSprite.getX() + 2 < 800) {
            playerSprite.translateX(2);
            this.setTexture(character + "r");
            this.direction = "RIGHT";
        }
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

    public void dispose() {
        playerSprite.getTexture().dispose();
        textureAtlas.dispose();
    }
}