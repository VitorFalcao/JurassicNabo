/**
 * This class defines the general behavior and properties of any entity of the game. These properties are: it's "x" and
 * "y" coordinates, it's speed on both "x" and "y" axis, it's width and height, wether it's falling and jumping and also
 * it stores a buffered image containg the corresponding image.
 *
 * @author Arthur Maia Mendes
 * @author Vitor Falcão da Rocha
 */
package Model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity implements IEntity {
	
	protected EntityId id;

	protected float x = 0, y = 0;
	protected float velX = 0, velY = 0;
	protected float width = 32, height = 32;
	
	protected boolean jumping = false;
	protected boolean falling = true;

	protected BufferedImage image = null;
	protected BufferedImageLoader loader = new BufferedImageLoader(); 
	
	public Entity(float x, float y, EntityId id) {

		setX(x);

		setY(y);

		this.id = id;
	}

	public EntityId getId() {

		return id;
	}

	public float getX() {

		return x;
	}

	public void setX(float x) {

		this.x = x;
	}

	public float getY() {

		return y;
	}

	public void setY(float y) {

		this.y = y;
	}
	
	public float getVelX() {

		return velX;
	}

	public void setVelX(float velX) {

		this.velX = velX;
	}

	public float getVelY() {

		return velY;
	}

	public void setVelY(float velY) {

		this.velY = velY;
	}
	
	public float getWidth() {

		return width;
	}

	public void setWidth(float width) {

		this.width = width;
	}

	public float getHeight() {

		return height;
	}

	public void setHeight(float height) {

		this.height = height;
	}
	
	public boolean isJumping() {

		return jumping;
	}

	public void setJumping(boolean jumping) {

		this.jumping = jumping;
	}
	
	public boolean isFalling() {

		return falling;
	}

	public void setFalling(boolean falling) {

		this.falling = falling;
	}

	public BufferedImage getImage() {

		return image;
	}

	public void setImage(BufferedImage image) {

		this.image = image;
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	public Rectangle getBoundsBottom() {

		return new Rectangle((int)(x + 0.25f*width), (int)(y + height - 1), (int)(width/2), 1);
	}
	
	public Rectangle getBoundsTop() {

		return new Rectangle((int)(x + 0.25f*width), (int)y, (int)width/2, 1);
	}

	public Rectangle getBoundsRight() {

		return new Rectangle((int)(x + width - 1), (int)(y + 0.25f*height), 1, (int)(height/2));
	}

	public Rectangle getBoundsLeft() {

		return new Rectangle((int)x, (int)(y + 0.25f*height), 1, (int)(height/2));
	}
}
