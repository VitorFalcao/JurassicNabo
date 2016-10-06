/**
 * This interface defines the way the controller talks to any given <code>Entity</code>.
 *
 * @author Vitor Falcão da Rocha
 */
package Model;


import java.awt.*;
import java.awt.image.BufferedImage;

public interface IEntity {

    /**
     * An ID (<code>EntityID</code>) of the current concrete implementation of <code>Entity</code>. This ID is
     * further used to check what kind of <code>Entity</code> the game is dealing with.
     *
     * @return The associated ID
     *
     * @see <code>Controller.Handler</code>, <code>Controller.Movement</code> and <code>Controller.KeyInput</code>
     */
    public EntityId getId();

    /**
     * Returns the "x" coordinate of the Entity. This coordinate is used for collision detection and for rendering the
     * associated image of this <code>Entity</code>.
     *
     * @return The "x" coordinate
     */
    public float getX();

    /**
     * Set's the <code>Entity</code> "x" coordinate.
     *
     * @param x the "x" coordinate of this <code>Entity</code>
     */
    public void setX(float x);

    /**
     * Returns the "y" coordinate of the Entity. This coordinate is used for collision detection and for rendering the
     * associated image of this <code>Entity</code>.
     *
     * @return The "y" coordinate
     */
    public float getY();

    /**
     * Set's the <code>Entity</code> "y" coordinate
     *
     * @param y the "y" coordinate of this <code>Entity</code>
     */
    public void setY(float y);

    /**
     * @return The speed on the "x" axis
     */
    public float getVelX();

    /**
     * Set's the <code>Entity</code> speed on the "x" axis.
     *
     * @param velX speed on the "x" axis
     */
    public void setVelX(float velX);

    /**
     * @return The speed on the "y" axis
     */
    public float getVelY();

    /**
     * Set's the <code>Entity</code> speed on the "y" axis.
     *
     * @param velY the speed on the "y" axis
     */
    public void setVelY(float velY);

    /**
     * Returns the <code>width</code> of the <code>Entity</code>. It's <code>width</code> is used for collision
     * detection and for.
     *
     * @return <code>Entity</code> <code>width</code>
     */
    public float getWidth();

    /**
     * @param width <code>Entity</code> width
     */
    public void setWidth(float width);

    /**
     * Returns the <code>height</code> of the <code>Entity</code>. It's <code>height</code> is used for collision
     * detection and for rendering.
     *
     * @return <code>Entity</code> <code>height</code>
     */
    public float getHeight();

    /**
     * @param height <code>Entity</code> <height
     */
    public void setHeight(float height);

    /**
     * @return <code>true</code> if the <code>Entity</code> is not touching the block, <code>false</code> otherwise
     */
    public boolean isJumping();

    /**
     * @param jumping a boolean telling if the <code>Entity</code> is jumping or not
     */
    public void setJumping(boolean jumping);

    /**
     * @return <code>true</code> if the <code>Entity</code> is falling, <code>false</code> otherwise
     */
    public boolean isFalling();

    /**
     * @param falling a boolean telling if the <code>Entity</code> is falling (<code>true</code>)
     * or not (<code>false</code>)
     */
    public void setFalling(boolean falling);

    /**
     * @return a buffered image that will be rendered
     */
    public BufferedImage getImage();

    /**
     * @param image a buffered image containing the <code>Entity</code> corresponding image.
     */
    public void setImage(BufferedImage image);

    /**
     * @return a rectangle containing the <code>Entity</code> (x,y) coordinates and dimensions
     */
    public Rectangle getBounds();

    /**
     * @return a rectangle containing the <code>Entity</code> bottom (x,y) coordinates and dimensions
     */
    public Rectangle getBoundsBottom();

    /**
     * @return a rectangle containing the <code>Entity</code> top (x,y) coordinates and dimensions
     */
    public Rectangle getBoundsTop();

    /**
     * @return a rectangle containing the <code>Entity</code> right (x,y) coordinates and dimensions
     */
    public Rectangle getBoundsRight();

    /**
     * @return a rectangle containing the <code>Entity</code> left (x,y) coordinates and dimensions
     */
    public Rectangle getBoundsLeft();
}