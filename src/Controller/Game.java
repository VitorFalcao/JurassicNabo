/**
 * This is main class of the game. It contains the game loop, creates the <code>Thread</code>, initialize the map and the
 * screen, and is the <code>Screen</code> delegate. As a delegate it is responsible for suplying the <code>Screen</code>
 * with the necessary data.
 *
 * @author Arthur Maia Mendes
 * @author Vitor Falcão da Rocha
 */

package Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

import Model.BufferedImageLoader;
import Model.EntityId;
import View.Screen;
import View.IScreenDelegate;
import View.Window;

public class Game implements Runnable, IScreenDelegate {
	
	
	private static final long serialVersionUID = 8660734185063805707L;
	
	public static final float WIDTH = 800.0f, HEIGHT = 608.0f;
	public static final float BLOCK_SIZE = 32.0f;
	public static int LEVEL = 1;
	public static boolean BOSS = false;
	public static boolean STARTED = false;
	public static boolean GAME_OVER = false;
	public static boolean GAME_WON = false;
	
	private boolean running = false;
	
	private Thread thread;
	
	private Handler handler;
	
	private Movement mov;
	
	private Collision coll;

    static Screen screen;

    private BufferedImageLoader loader = new BufferedImageLoader();

    public Game() {

        screen = new Screen();

        screen.delegate = this;

        new Window ((int)WIDTH, (int)HEIGHT, "Jurassic Nabo", screen);
    }

    /**
     * Starts the game, creating the <code>Thread</code> and setting some of the game's initial state.
     */
	public synchronized void start() {

		if (running) {

            return;
        }

		running = true;
		thread = new Thread(this);
		thread.start();
	}

    /**
     * This is the game main loop. It is responsible for running the game, finishing it's initialization and asking the
     * view to update the screen every cycle.
     */
	@Override
	public void run() {
		init();
		screen.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			screen.render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

    /**
     * This method finish initializing the game. As part of this process, it asks the view to initialize itself and also
     * adds a key listener to the <code>Screen</code>
     */
	private void init() {

		handler = new Handler();
		handler.loadLevel();
		
		mov = new Movement(handler);
		
		coll = new Collision(handler);

        screen.init();

        screen.addKeyListener(new KeyInput(handler, mov));
	}

    /**
     * This method gets called every cycle. I makes the game keep moving, always asks for the <code>Collision</code> class
     * to check for collisions and also moves the camera according to the player's position.
     */
	private void tick() {
		
		mov.makeMovements();
		
		coll.checkCollisions();
		
		for (int i = 0; i < handler.entity.size(); i++) {
			if (handler.entity.get(i).getId() == EntityId.Player) {
				screen.getCam().move(handler.entity.get(i));
			}
		}
	}

    /**
     * This method is part of the delegate interface. It aks the <code>loader</code> to buffer the requested image.
     *
     * @param path the image path
     * @return a buffered image object
     */
    public BufferedImage loadImage(String path) {

        return loader.loadImage(path);
    }

    /**
     * This method is part of the delegate interface. It asks the <code>Handler</code> to render the requested <code>
     * Graphics</code> object.
     *
     * @param g the graphics object to be used for drawing to the screen
     */
	public void render(Graphics g) {
		
		handler.render(g);
	}
}
