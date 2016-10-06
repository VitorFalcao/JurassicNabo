/**
 * This class is responsible for loading the levels, loading the entities, changing levels and rendering desired images,
 * in association with the <code>Screen</code>
 *
 * @author Arthur Maia Mendes
 * @author Vitor Falcão da Rocha
 */

package Controller;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import Model.IEntity;
import Model.EntityFactory;
import Model.EntityId;


public class Handler {

	private static final int SIZE = 3;
	
	private EntityFactory factory = new EntityFactory();
	
	public LinkedList<IEntity> entity = new LinkedList<IEntity>();
	
	private IEntity tempEntity;

    /**
     * Loads the level entity according to the game state.
     */
	public void loadLevel() {
		
		loadBlocks();
		loadPlayer();
		if (!Game.BOSS) {
			loadDinosaur();
			loadFlag();
			loadObstacles();
		}
        else {
			loadBoss();
			loadBossLifeMeter();
			loadPlayerLifeMeter();
		}
		
	}

    /**
     * Loads the blocks according to the map dimensions and the current level.
     */
	private void loadBlocks() {
		
		for (int yy = (int)(Game.HEIGHT - 2*Game.BLOCK_SIZE); yy >= 0; yy -= Game.BLOCK_SIZE)
			addEntity(factory.getEntity(0, yy, EntityId.Block));
		
		if (!Game.BOSS){
			for (int xx= 0; xx < Game.WIDTH*SIZE*Game.LEVEL; xx += Game.BLOCK_SIZE) {
				addEntity(factory.getEntity(xx, Game.HEIGHT - Game.BLOCK_SIZE, EntityId.Block));
				addEntity(factory.getEntity(xx, 0, EntityId.Block));
			}
			
			for (int yy = (int)(Game.HEIGHT - 2*Game.BLOCK_SIZE); yy > 0; yy -= Game.BLOCK_SIZE)
				addEntity(factory.getEntity(Game.WIDTH*SIZE*Game.LEVEL - Game.BLOCK_SIZE, yy, EntityId.Block));
		}
		else {
			for (int xx= 0; xx < Game.WIDTH; xx += Game.BLOCK_SIZE) {
				addEntity(factory.getEntity(xx, Game.HEIGHT - Game.BLOCK_SIZE, EntityId.Block));
				addEntity(factory.getEntity(xx, 0, EntityId.Block));
			}
			
			for (int yy = (int)(Game.HEIGHT - 2*Game.BLOCK_SIZE); yy > 0; yy -= Game.BLOCK_SIZE)
				addEntity(factory.getEntity(Game.WIDTH - Game.BLOCK_SIZE, yy, EntityId.Block));
		}
	}

    /**
     * Loads the player entity according to the map dimensions.
     */
	private void loadPlayer() {

        addEntity(factory.getEntity(3f*Game.BLOCK_SIZE, 0.75f*Game.HEIGHT, EntityId.Player));
	}

    /**
     * Loads the dinosaur entity according to the map dimensions.
     */
	private void loadDinosaur() {

		addEntity(factory.getEntity(1.5f*Game.BLOCK_SIZE, 0.75f*Game.HEIGHT, EntityId.Dinosaur));
	}

    /**
     * Loads the flag entity according to the map dimensions and the current game level.
     */
	private void loadFlag() {
		addEntity(factory.getEntity(Game.WIDTH*SIZE*Game.LEVEL - 2.5f*Game.BLOCK_SIZE, Game.HEIGHT - Game.BLOCK_SIZE - 50, EntityId.Flag));
	}

    /**
     * Loads the the obstacles (plant, rock or hole) into the map according to the map dimensions.
     */
	private void loadObstacles() {

		Random rand = new Random();

		int interval;

		for (int xx = (int)(10f*Game.BLOCK_SIZE); xx < Game.WIDTH*SIZE*Game.LEVEL - 5f*Game.BLOCK_SIZE; xx += interval*Game.BLOCK_SIZE) {

			int obstacle = rand.nextInt(3);

			interval = rand.nextInt(10) + 5;

			switch (obstacle) {
				case 0:
					addEntity(factory.getEntity(xx, Game.HEIGHT - 3.1f*Game.BLOCK_SIZE, EntityId.Plant));
					break;
				case 1:
					addEntity(factory.getEntity(xx, Game.HEIGHT - 2f*Game.BLOCK_SIZE, EntityId.Rock));
					break;
				case 2:
					addEntity(factory.getEntity(xx, Game.HEIGHT - Game.BLOCK_SIZE, EntityId.Hole));
					removeBlocks(xx, Game.HEIGHT - Game.BLOCK_SIZE);
					break;
			}
		}
	}

    /**
     * Loads the game boss according to the map dimensions. This method only gets called on the boss' stages.
     */
	private void loadBoss() {

		addEntity(factory.getEntity(0.25f*Game.WIDTH, 0.25f*Game.HEIGHT, EntityId.Boss));
	}

    /**
     * Loads the five ball saint according to the map dimensions. This method only gets called on the boss' stages.
     */
	public void loadFiveBallSaint() {

		addEntity(factory.getEntity(Game.WIDTH - 2.5f*Game.BLOCK_SIZE, 1.5f*Game.BLOCK_SIZE, EntityId.FiveBallSaint));
	}

    /**
     * Loads the boss life meter according to the map dimensions. This method only gets called on the boss' stages.
     */
	private void loadBossLifeMeter() {

		addEntity(factory.getEntity(2.5f*Game.BLOCK_SIZE, 0.25f*Game.HEIGHT, EntityId.BossLifeMeter));
	}

    /**
     * Loads the player life meter according to the map dimensions. This method only gets called on the boss' stages.
     */
	private void loadPlayerLifeMeter() {

		addEntity(factory.getEntity(Game.WIDTH - 2.5f*Game.BLOCK_SIZE, 0.25f*Game.HEIGHT, EntityId.PlayerLifeMeter));
	}

    /**
     * Loads the question according to the map dimensions. This method only gets called on the boss stages and when the
     * player fires it.
     * @param entity this parameter represents the <code>Entity</code> that is shooting the <code>Question</code>.
     */
	public void loadQuestion(IEntity entity) {

		addEntity(factory.getEntity(entity.getX(), entity.getY(), EntityId.Question));
	}

    /**
     * Loads the question according to the map dimensions. This method only gets randomly called on the boss' stages.
     * @param entity this parameter represents the <code>Entity</code> that is shooting the <code>Question</code>.
     */
	public void loadExam(IEntity entity) {

		addEntity(factory.getEntity(entity.getX(), entity.getY(), EntityId.Exam));
	}

    /**
     * Insert the five ball saint into the map
     */
	public void useFiveBallSaint() {

        for (int i = 0; i < entity.size(); i++) {

			tempEntity = entity.get(i);
			
			if (tempEntity.getId() == EntityId.FiveBallSaint) {

                removeEntity(tempEntity);
			}
			
			if (tempEntity.getId() == EntityId.PlayerLifeMeter) {

                tempEntity.setHeight(150);
			}
		}
	}

    /**
     * Removes blocks that are no longer displayed
     *
     * @param x coordinate
     * @param y coordinate
     */
	private void removeBlocks(float x, float y) {

		for (int i = 0; i < entity.size(); i++) {

			tempEntity = entity.get(i);
			
			if (tempEntity.getId() == EntityId.Block && tempEntity.getY() == y) {

                if (tempEntity.getX() == x || tempEntity.getX() == x + 32 || tempEntity.getX() == x - 32) {

                    removeEntity(tempEntity);
                }
            }
		}
	}

    /**
     *
     * @param g the graphics object used to draw onto the screen
     */
	public void render(Graphics g) {

		for (int i = 0; i < entity.size(); i++) {

            tempEntity = entity.get(i);

			Game.screen.drawImageOnScreen(g, tempEntity.getImage(), tempEntity.getX(), tempEntity.getY(), tempEntity.getWidth(), tempEntity.getHeight());
		}
	}

    /**
     * Switches the level and checks if the game was won or not.
     */
	public void switchLevel() {
		
		clearLevel();

		Game.screen.getCam().setX(0);
		
		if (Game.BOSS && Game.LEVEL == 3) 
			Game.GAME_WON = true;
		else {
			if (Game.BOSS)
				Game.LEVEL++;
			
			Game.BOSS = !Game.BOSS;

			
			loadLevel();
		}
	}

    /**
     *
     * @param entity to be removed from the list
     */
	public void addEntity(IEntity entity) {
		this.entity.add(entity);
	}

    /**
     *
     * @param entity to be added to the list
     */
	public void removeEntity(IEntity entity) {
		this.entity.remove(entity);
	}

    /**
     * Clears the entities list, in order to create a new level.
     */
	public void clearLevel() {

		entity.clear();
	}
}
