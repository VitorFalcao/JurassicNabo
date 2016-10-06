/**
 * A <code>Block</code> class represents an abstraction of a game block. It defines it's dimensions and associates an
 * image to it. Other properties like it's (x,y) coordinates and it's id are also defined, using it's parent class
 * constructor.
 *
 * @author Arthur Maia Mendes
 */

package Model;

public class Block extends Entity {

	public Block(float x, float y, EntityId id) {

		super(x, y, id);

		width = 32;
		height = 32;
		image = loader.loadImage("/block.png");
	}
}
