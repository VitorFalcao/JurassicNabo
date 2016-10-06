/**
 * A <code>Flag</code> class represents an abstraction of a game flag. The flag is used to detect when the player
 * reaches the end of a level. It defines it's dimensions and associates an image to it. Other properties like it's
 * (x,y) coordinates and it's id are also defined, using it's parent class constructor.
 *
 * @author Arthur Maia Mendes
 */

package Model;

public class Flag extends Entity{

	public Flag(float x, float y, EntityId id) {
		super(x, y, id);
		width = 33;
		height = 50;
		image = loader.loadImage("/flag.png");
	}
}
