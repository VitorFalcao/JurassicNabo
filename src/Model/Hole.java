/**
 * A <code>Hole</code> class represents an abstraction of a game hole. It defines it's dimensions and associates an
 * image to it. Other properties like it's (x,y) coordinates and it's id are also defined, using it's parent class
 * constructor.
 *
 * @author Arthur Maia Mendes
 */

package Model;

public class Hole extends Entity {

	public Hole(float x, float y, EntityId id) {
		super(x, y, id);
		width = 0;
		height = 0;
		image = loader.loadImage("/hole.png");
	}
}
