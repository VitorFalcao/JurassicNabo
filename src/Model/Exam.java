/**
 * An <code>Exam</code> class represents an abstraction of a game exam. It defines it's dimensions and associates an
 * image to it. It also defines the direction in which the exam will be thrown. Other properties like it's (x,y)
 * coordinates and it's id are also defined, using it's parent class constructor.
 *
 * @author Arthur Maia Mendes
 * @author Vitor Falcão da Rocha
 */

package Model;

import java.util.Random;

public class Exam extends Entity {

	private Random rand = new Random();
	private int direction;
	
	public Exam(float x, float y, EntityId id) {
		super(x, y, id);
		width = 16;
		height = 32;
		setVelY(5);
		
		direction = rand.nextInt(4);
		
		switch (direction) {
            case 0:
                setVelX(0);
                break;
            case 1:
                setVelX(2);
                break;
            case 2:
                setVelX(-2);
                break;
            case 3:
                setVelX(5);
                break;
            case 4:
                setVelX(-5);
                break;
	}
		
		image = loader.loadImage("/exam.png");
	}
}
