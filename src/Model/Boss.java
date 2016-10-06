/**
 * A <code>Boss</code> class represents an abstraction of a game boss. It defines it's dimensions and associates an
 * image to it. Other properties like it's (x,y) coordinates and it's id are also defined, using it's parent class
 * constructor.
 *
 * @author Arthur Maia Mendes
 */

package Model;

public class Boss extends Entity{

	public Boss(float x, float y, EntityId id) {
		super(x, y, id);
		width = 32;
		height = 48;
		image = loader.loadImage("/boss.png");
	}
}
