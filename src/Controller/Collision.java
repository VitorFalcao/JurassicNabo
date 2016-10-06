/**
 * This class is responsible for detecting collision between a given <code>IEntity</code> and the other map objetcs.
 *
 * @author Arthur Maia Mendes
 * @author Vitor Falcão da Rocha
 */

package Controller;

import Model.IEntity;
import Model.EntityId;

public class Collision {
	
	private Handler handler;
	private IEntity tempEntity;
	
	Movement mov;
	
	public Collision(Handler handler) {
		this.handler = handler;
		mov = new Movement(handler);
	}
	
	public void checkCollisions() {
		
		for (int i = 0; i < handler.entity.size(); i++) {
			tempEntity = handler.entity.get(i);
			
			if (tempEntity.getId() == EntityId.Player)
				playerCollision(tempEntity);
			
			if (!Game.BOSS) {
				
				if (tempEntity.getId() == EntityId.Dinosaur)
					dinosaurCollision(tempEntity);
				
			} else {
				
				if (tempEntity.getId() == EntityId.Question)
					questionCollision(tempEntity);
				
				if (tempEntity.getId() == EntityId.Exam)
					examCollision(tempEntity);
			}
		}
	}

	private void dinosaurCollision(IEntity entity) {
		for (int i = 0; i < handler.entity.size(); i ++) {
			
			tempEntity = handler.entity.get(i);
			
			if (tempEntity.getId() == EntityId.Block) {
				if (entity.getBounds().intersects(tempEntity.getBounds())) {

					if (entity.getBoundsBottom().intersects(tempEntity.getBounds())) {
						entity.setY(tempEntity.getY() - entity.getHeight());
						mov.changeVelocity(entity, 0, true);
						entity.setFalling(false);
						entity.setJumping(false);
						
					} else
						entity.setFalling(true);
				}
				
			}
			if (tempEntity.getId() == EntityId.Rock || tempEntity.getId() == EntityId.Plant)
				if (entity.getBounds().intersects(tempEntity.getBounds()))
					handler.removeEntity(tempEntity);
		}
	}

	private void questionCollision(IEntity entity) {
		
		for (int i = 0; i < handler.entity.size(); i ++) {
			
			tempEntity = handler.entity.get(i);
			
			if (tempEntity.getId() == EntityId.Boss) {
				
				if (entity.getBounds().intersects(tempEntity.getBounds())) {
					handler.removeEntity(entity);
					
					for (int j = 0; j < handler.entity.size(); j ++)
						if (handler.entity.get(j).getId() == EntityId.BossLifeMeter) {
							if (Game.LEVEL == 1)
								handler.entity.get(j).setHeight(handler.entity.get(j).getHeight() - 30);
							if (Game.LEVEL == 2)
								handler.entity.get(j).setHeight(handler.entity.get(j).getHeight() - 15);
							if (Game.LEVEL == 3)
								handler.entity.get(j).setHeight(handler.entity.get(j).getHeight() - 9);
							
							if (handler.entity.get(j).getHeight() <= 0) {
								handler.removeEntity(tempEntity);
								handler.switchLevel();
							}
						}
				}
			}
		}
	}

	private void examCollision(IEntity entity) {
		
		for (int i = 0; i < handler.entity.size(); i ++) {
			
			tempEntity = handler.entity.get(i);
			
			if (tempEntity.getId() == EntityId.Player) {
				
				if (entity.getBounds().intersects(tempEntity.getBounds())) {
					handler.removeEntity(entity);
					
					for (int j = 0; j < handler.entity.size(); j ++)
						if (handler.entity.get(j).getId() == EntityId.PlayerLifeMeter) {
							handler.entity.get(j).setHeight(handler.entity.get(j).getHeight() - 30);
							
							if (handler.entity.get(j).getHeight() <= 90) 
								handler.loadFiveBallSaint();
							
							if (handler.entity.get(j).getHeight() <= 0) {
								handler.removeEntity(tempEntity);
								Game.GAME_OVER = true;
							}
						}
				}
			}
		}
	}

	private void playerCollision(IEntity entity) {
		
		for (int i = 0; i < handler.entity.size(); i ++) {
			
			tempEntity = handler.entity.get(i);
			
			if (tempEntity.getId() == EntityId.Block) {
					
				if (entity.getBoundsBottom().intersects(tempEntity.getBounds())) {
					entity.setY(tempEntity.getY() - entity.getHeight());
					mov.changeVelocity(entity, 0, true);
					entity.setFalling(false);
					entity.setJumping(false);
				} else {
					entity.setFalling(true);
				}
				
				if (entity.getBoundsTop().intersects(tempEntity.getBounds())) {
					entity.setY(tempEntity.getY() + tempEntity.getHeight());
					mov.changeVelocity(entity, 0, true);
				}
				
				if (entity.getBoundsRight().intersects(tempEntity.getBounds()))
					entity.setX(tempEntity.getX() - entity.getWidth() - 1);
				
				if (entity.getBoundsLeft().intersects(tempEntity.getBounds()))
					entity.setX(tempEntity.getX() + tempEntity.getWidth() + 1);
			}
			
			if (tempEntity.getId() == EntityId.Flag) {
				
				if (entity.getBounds().intersects(tempEntity.getBounds())) {
					handler.switchLevel();
				}
			}
			
			if (tempEntity.getId() == EntityId.Rock) {
				if (entity.getBounds().intersects(tempEntity.getBounds())) {
					handler.removeEntity(tempEntity);
					
					if (entity.getVelX() > 1) 
						mov.changeVelocity(entity, entity.getVelX() - 2, false);
				}
			}
			
			if (tempEntity.getId() == EntityId.Plant || tempEntity.getId() == EntityId.Dinosaur) {
				if (entity.getBounds().intersects(tempEntity.getBounds())) {
					mov.changeVelocity(entity, 0, false);
					Game.GAME_OVER = true;
				}
				else if(tempEntity.getId() == EntityId.Dinosaur && entity.getX() <= tempEntity.getX()) {

                    mov.changeVelocity(entity, 0, false);
                    Game.GAME_OVER = true;
                }
			}
			
		}
	}
}
