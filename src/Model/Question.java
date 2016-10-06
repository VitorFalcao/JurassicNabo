/**
 * A <code>Question</code> class represents an abstraction of a game question. It defines it's dimensions and associates
 * an image to it. Other properties like it's (x,y) coordinates and it's id are also defined, using it's parent class
 * constructor.
 *
 * @author Arthur Maia Mendes
 */

package Model;

public class Question extends Entity{

	public Question(float x, float y, EntityId id) {
		super(x, y, id);
		width = 16;
		height = 32;
		setVelY(-5);
		image = loader.loadImage("/question.png");
	}
}
