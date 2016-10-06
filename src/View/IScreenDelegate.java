/**
 * This interface defines the way <code>Screen</code> will talk to the Controller.
 *
 * @author Vitor Falcão da Rocha
 */

package View;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IScreenDelegate {

    public void start();

    public BufferedImage loadImage(String path);

    public void render(Graphics g);
}
