/**
 * This class creates the game main window and attaches to it a <code>Screen</code>. This class also calls the <code>
 * start</code> method of the <code>Screen</code> class to initialize and start the game.
 *
 * @author Arthur Maia Mendes
 * @author Vitor Falcão da Rocha
 *
 * @see <code>View.Screen</code>
 */

package View;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {

	public Window(int w, int h, String title, Screen game) {


		game.setPreferredSize(new Dimension(w, h));
        game.setMaximumSize(new Dimension(w, h));
        game.setMinimumSize(new Dimension(w, h));

		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}
