/**
 * This class is responsible for receiving and interpreting all the key inputs, generating actions according to the game
 * state.
 *
 * @author Arthur Maia Mendes
 */

package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Model.EntityId;
import Model.IEntity;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private Movement mov;
	private boolean shot = false;
	
	public KeyInput(Handler handler, Movement mov) {
		this.handler = handler;
		this.mov = mov;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

        /**
         * Restart's the game.
         */
        if (key == KeyEvent.VK_R) {

            handler.clearLevel();

            Game.screen.getCam().setX(0);

            Game.LEVEL = 1;
            Game.BOSS = false;
            Game.GAME_OVER = false;
            Game.GAME_WON = false;
            Game.STARTED = false;

            handler.loadLevel();
        }

		for (int i = 0; i < handler.entity.size(); i++) {

			IEntity tempEntity = handler.entity.get(i);
			
			if(tempEntity.getId() == EntityId.Player) {
				
				if (Game.BOSS) {

                    if (key == KeyEvent.VK_D) {

                        mov.changeVelocity(tempEntity, 5, false);
                    }
                    else if (key == KeyEvent.VK_A) {

                        mov.changeVelocity(tempEntity, -5, false);
                    }
                }

                if(key == KeyEvent.VK_W && !tempEntity.isJumping() && !(tempEntity.getY() + tempEntity.getHeight() > Game.HEIGHT - 0.75f*Game.BLOCK_SIZE)) {

                    tempEntity.setJumping(true);

                    mov.changeVelocity(tempEntity, -10, true);
				}

                if(key == KeyEvent.VK_SPACE) {

                    if (!Game.STARTED) {

                        mov.setInitialVelocities();

                        Game.STARTED = true;
					}
                    else if (!shot && Game.BOSS) {

                        handler.loadQuestion(tempEntity);

                        shot = true;
					}
				}
			}
			
			if(tempEntity.getId() == EntityId.PlayerLifeMeter) {

				if (tempEntity.getHeight() <= 90 && tempEntity.getHeight() > 0) {

                    if (key == KeyEvent.VK_5) {

                        handler.useFiveBallSaint();
                    }
                }
			}
		}
			
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.entity.size(); i++) {

			IEntity tempEntity = handler.entity.get(i);

			if(tempEntity.getId() == EntityId.Player && Game.BOSS == true) {

				if(key == KeyEvent.VK_D) {

                    tempEntity.setVelX(0);
                }
				else if(key == KeyEvent.VK_A) {

                    tempEntity.setVelX(0);
                }
			}
			
			if(key == KeyEvent.VK_SPACE) {

                shot = false;
            }
		}
	}
}
