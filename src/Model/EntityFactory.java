/**
 * This class is an <code>Entity</code> factory. It's single method is used to receive (x,y) coordinates, an
 * <code>EntityId</code>, identify the requested <code>Entity</code>, create it with the (x,y) coordinates received and
 * id, then return the newly created <code>Entity</code>
 *
 * @author Arhtur Maia Mendes
 */

package Model;

public class EntityFactory {

	public Entity getEntity(float x, float y, EntityId id){
	    
		if(id == null) {
			return null;
	    }
        else if(id == EntityId.Player) {

	    		return new Player(x, y, id);
	    }
        else if(id == EntityId.Block) {

            return new Block(x, y, id);
	    }
        else if(id == EntityId.Question) {

            return new Question(x, y, id);
	    }
        else if(id == EntityId.Flag) {

            return new Flag(x, y, id);
	    }
        else if(id == EntityId.Plant) {

            return new Plant(x, y, id);
	    }
        else if(id == EntityId.Rock) {

            return new Rock(x, y, id);
	    }
        else if(id == EntityId.Hole) {

            return new Hole(x, y, id);
	    }
        else if(id == EntityId.FiveBallSaint) {

            return new FiveBallSaint(x, y, id);
	    }
        else if(id == EntityId.Dinosaur) {

            return new Dinosaur(x, y, id);
	    }
        else if(id == EntityId.Boss) {

            return new Boss(x, y, id);
	    }
        else if(id == EntityId.Exam) {
	    	return new Exam(x, y, id);
	    }
        else if(id == EntityId.BossLifeMeter) {

	    	return new BossLifeMeter(x, y, id);
	    }
        else if(id == EntityId.PlayerLifeMeter) {

	    	return new PlayerLifeMeter(x, y, id);
	    }

        return null;
	}
}