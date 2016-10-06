/**
 * This class represents the camera that will follow the <code>Player</code> along the game.
 *
 * @author Arthur Maia Mendes
 */

package View;

import Controller.Game;
import Model.IEntity;

public class Camera {

    private float x, y;

    /**
     * @param x coordinate of the camera position
     * @param y coordinate of the camera position
     */
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

    /**
     * This method updates the camera's <code>x</code> coordinate according to a given <code>IEntity</code> position.
     *
     * @param player entity which the camera must follow
     */
	public void move(IEntity player) {
		x = -player.getX() + Game.WIDTH/2;
	}

    /**
     * @return camera's current "x" coordinate
     */
	public float getX() {
		return x;
	}

    /**
     * @param x coordinate of the camera
     */
	public void setX(float x) {
		this.x = x;
	}

    /**
     * @return camera's current "y" coordinate
     */
	public float getY() {
		return y;
	}

    /**
     * @param y coordinate of the camera
     */
	public void setY(float y) {
		this.y = y;
	}
}
