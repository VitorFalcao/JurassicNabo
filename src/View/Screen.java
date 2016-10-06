/**
 * @author Arthur Maia Mendes
 * @author Vitor Falcão da Rocha
 */
package View;

import Controller.Game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Screen extends Canvas {

    public IScreenDelegate delegate;

    private Camera cam;
    private BufferedImage bkg, gameover, gamewon;

    public void start() {

        System.out.println("PE");

        delegate.start();
    }

    public void init() {

        cam = new Camera(0, 0);

        bkg = delegate.loadImage("/bkg.png");
        gameover = delegate.loadImage("/gameover.png");
        gamewon = delegate.loadImage("/gamewon.png");
    }

    /**
     * @return <code>cam</code>
     */
    public Camera getCam() {

        return this.cam;
    }

    /**
     * This method renders... TODO: FINISH DESCRIPTION
     */
    public void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null)  {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.black);
        g.fillRect(0, 0, (int)Game.WIDTH, (int)Game.HEIGHT);

        if (Game.GAME_OVER) {

            g.drawImage(gameover, 0, 0, (int)Game.WIDTH, (int)Game.HEIGHT, this);

        }
        else if (Game.GAME_WON) {

            g.drawImage(gamewon, 0, 0, (int)Game.WIDTH, (int)Game.HEIGHT, this);

        }
        else if (Game.BOSS) {

            g.drawImage(bkg, 0, 0, (int)Game.WIDTH, (int)Game.HEIGHT, this);

            delegate.render(g);

        }
        else {

            g.drawImage(bkg, 0, 0, (int)Game.WIDTH, (int)Game.HEIGHT, this);

            g2d.translate(cam.getX(), cam.getY()); // begin of cam

            delegate.render(g);

            g2d.translate(cam.getX(), cam.getY()); // end of cam
        }

        g.dispose();
        bs.show();
    }

    public void drawImageOnScreen(Graphics g, BufferedImage image, float x, float y, float width, float height) {



        g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
    }
}
