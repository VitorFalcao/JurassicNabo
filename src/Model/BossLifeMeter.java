/**
 * A <code>BossLifeMeter</code> class represents an abstraction of a game boos life meter. It defines it's dimensions
 * and associates an image to it. Other properties like it's (x,y) coordinates and it's id are also defined, using it's
 * parent class constructor.
 *
 * @author Arthur Maia Mendes
 */

package Model;

public class BossLifeMeter extends Entity{

	public BossLifeMeter(float x, float y, EntityId id) {
		super(x, y, id);
		width = 16;
		height = 300;
		image = loader.loadImage("/bosslifemeter.png");
	}
}
