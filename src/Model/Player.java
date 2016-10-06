/**
 * A <code>Player</code> class represents an abstraction of a game player. It defines it's dimensions and associates an
 * image to it. Other properties like it's (x,y) coordinates and it's id are also defined, using it's parent class
 * constructor.
 *
 * @author Arthur Maia Mendes
 */

package Model;

public class Player extends Entity {
	
	public Player(float x, float y, EntityId id) {
		super(x, y, id);
		width = 16;
		height = 28;
		image = loader.loadImage("/player.png");
	}
}
