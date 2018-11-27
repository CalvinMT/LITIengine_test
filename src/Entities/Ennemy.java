package Entities;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import de.gurkenlabs.litiengine.physics.MovementController;

public class Ennemy extends Creature {
	
	public Ennemy () {
		this.setName("ennemy");
		this.setTeam(101);
		this.initialize();
	}
	
	
	
	private void initialize () {
		// TODO - random level of ennemy
		this.setController(MovementController.class, new EnnemyController(this));
		this.setController(EntityAnimationController.class, new EnnemyAnimationController(this));
	}
	
}
