/**
 * A <code>FiveBallSaint</code> class represents an abstraction of a game five ball saint. It defines it's dimensions
 * and associates an image to it. Other properties like it's (x,y) coordinates and it's id are also defined, using it's
 * parent class constructor.
 *
 * @author Arthur Maia Mendes
 * @author Vitor Falcão da Rocha
 */

package Model;

public class FiveBallSaint extends Entity{

	public FiveBallSaint(float x, float y, EntityId id) {
		super(x, y, id);
		width = 32;
		height = 32;
		image = loader.loadImage("/fiveballsaint.png");
	}
}
