/**
 * This class and it's single method are designed to receive a <code>String</code> representing the path to an image and
 * return a <code>BufferedImage</code> containing the received image.
 *
 * @author Arthur Maia Mendes
 */

package Model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
		
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
