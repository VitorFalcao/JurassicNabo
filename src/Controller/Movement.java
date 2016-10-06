/**
 * This class makes a given <code>Entity</code> move following some rules and map aspects and state.
 */

package Controller;

import Controller.Game;

import Model.IEntity;
import Model.EntityId;

public class Movement {
	
	private float gravity = 0.5f;
	
	private Handler handler;
	
	private IEntity tempEntity;
	
	public Movement(Handler handler) {
		this.handler = handler;
	}
	
	public void makeMovements() {
		for (int i = 0; i < handler.entity.size(); i++) {
			tempEntity = handler.entity.get(i);
			
			if (tempEntity.getId() == EntityId.Player)
				playerMovement(tempEntity);
			
			if (!Game.BOSS) {
				if (tempEntity.getId() == EntityId.Dinosaur)
					dinosaurMovement(tempEntity);
			} else {
				if (tempEntity.getId() == EntityId.Question)
					questionMovement(tempEntity);
				if (tempEntity.getId() == EntityId.Boss)
					bossMovement(tempEntity);
				if (tempEntity.getId() == EntityId.Exam)
					examMovement(tempEntity);
			}
					
		}
	}
	
	private void playerMovement(IEntity entity) {
		
		entity.setX(entity.getX() + entity.getVelX());
		
		entity.setY(entity.getY() + entity.getVelY());
		
		if (entity.isJumping() || entity.isFalling())
			changeVelocity(entity, entity.getVelY() + gravity, true);
		
		if (entity.getY() > Game.HEIGHT) {
			handler.removeEntity(entity);
			Game.GAME_OVER = true;
		}
	}
	
	private void dinosaurMovement(IEntity entity) {
		entity.setX(entity.getX() + entity.getVelX());
		
		entity.setY(entity.getY() + entity.getVelY());
		
		if (entity.isJumping() || entity.isFalling())
			changeVelocity(entity, entity.getVelY() + gravity, true);
	}
	
	private void bossMovement(IEntity entity) {
		entity.setX(entity.getX() + entity.getVelX());
		
		if (entity.getX() < 0.25f*Game.WIDTH || entity.getX() > 0.75f*Game.WIDTH) 
			changeVelocity(entity, -entity.getVelX(), false);
		
		if (Game.STARTED && entity.getX() % (60/Game.LEVEL) == 0) handler.loadExam(entity);
	}
	
	private void questionMovement(IEntity entity) {
		entity.setY(entity.getY() + entity.getVelY());
		
		if (entity.getY() < 0) handler.removeEntity(entity); 
	}
	
	private void examMovement(IEntity entity) {
		entity.setY(entity.getY() + entity.getVelY());
		
		entity.setX(entity.getX() + entity.getVelX());
		
		if (entity.getY() > Game.HEIGHT) handler.removeEntity(entity);
	}

	public void setInitialVelocities() {
		if (Game.BOSS)
			for (int i = 0; i < handler.entity.size(); i++) {
				tempEntity = handler.entity.get(i);
				
				if (tempEntity.getId() == EntityId.Boss)
					changeVelocity(tempEntity, 5, false);			
			}
		else
			for (int i = 0; i < handler.entity.size(); i++) {
				tempEntity = handler.entity.get(i);
				
				if (tempEntity.getId() == EntityId.Player)
					changeVelocity(tempEntity, 5, false);
				
				if (tempEntity.getId() == EntityId.Dinosaur)
					changeVelocity(tempEntity, 3, false);
			}
	}
	
	public void changeVelocity(IEntity entity, float v, boolean vert) {
		
		if (vert)
			entity.setVelY(v);
		else 
			entity.setVelX(v);
	}
	
}
