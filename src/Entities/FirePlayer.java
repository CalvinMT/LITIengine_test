package Entities;
import Engine.MoreRotation;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CombatEntity;
import de.gurkenlabs.litiengine.entities.CombatEntityHitEvent;
import de.gurkenlabs.litiengine.entities.CombatEntityListener;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;

public class FirePlayer extends CombatEntity implements IUpdateable {
	
	private MoreRotation rotation;
	final private double speed = 10.0f;
	
	protected FirePlayer (Player player) {
		this.setName("fire_player");
		this.setTeam(1);
		this.initialize();
		this.initializeLocationRotation(player);
		this.initializeEvents();
	}
	
	
	
	private void initialize () {
		this.setController(EntityAnimationController.class, new FirePlayerAnimationController(this));
	}
	
	private void initializeLocationRotation (Player player) {
		switch (player.getRotation()) {
			case NONE:
				this.setLocation(player.getX(), player.getY()-player.getHeight());
				this.getAnimationController().playAnimation("fire_player_top_16");
				break;
			case ROTATE_45:
				this.setLocation(player.getX()+player.getWidth(), player.getY()-player.getHeight());
				this.getAnimationController().playAnimation("fire_player_top_right_16");
				break;
			case ROTATE_90:
				this.setLocation(player.getX()+player.getWidth(), player.getY());
				this.getAnimationController().playAnimation("fire_player_right_16");
				break;
			case ROTATE_135:
				this.setLocation(player.getX()+player.getWidth(), player.getY()+player.getHeight());
				this.getAnimationController().playAnimation("fire_player_bottom_right_16");
				break;
			case ROTATE_180:
				this.setLocation(player.getX(), player.getY()+player.getHeight());
				this.getAnimationController().playAnimation("fire_player_bottom_16");
				break;
			case ROTATE_225:
				this.setLocation(player.getX()-player.getWidth(), player.getY()+player.getHeight());
				this.getAnimationController().playAnimation("fire_player_bottom_left_16");
				break;
			case ROTATE_270:
				this.setLocation(player.getX()-player.getWidth(), player.getY());
				this.getAnimationController().playAnimation("fire_player_left_16");
				break;
			case ROTATE_315:
				this.setLocation(player.getX()-player.getWidth(), player.getY()-player.getHeight());
				this.getAnimationController().playAnimation("fire_player_top_left_16");
				break;
		}
		this.rotation = player.getRotation();
	}
	
	private void initializeEvents () {
		this.addCombatEntityListener(new CombatEntityListener() {
			
			@Override
			public void onDeath(ICombatEntity entity) {
				Game.getLoop().detach((FirePlayer) entity);
				Game.getEnvironment().remove((FirePlayer) entity);
			}
			
			@Override
			public void onHit(CombatEntityHitEvent event) {
				System.out.println("LIVE OR LET");
				/*if (event.getEntity().canCollideWith(event.)) {
					event.getEntity().die();
					System.out.println("DIE");
				}*/
			}
			
			@Override
			public void onResurrection(ICombatEntity entity) {
				// Does not resurrect
			}
		});
	}
	
	
	
	private void moveFire () {
		switch (this.rotation) {
			case NONE:
				this.setY(this.getY() - this.speed);
				break;
			case ROTATE_45:
				this.setY(this.getY() - this.speed);
				this.setX(this.getX() + this.speed);
				break;
			case ROTATE_90:
				this.setX(this.getX() + this.speed);
				break;
			case ROTATE_135:
				this.setY(this.getY() + this.speed);
				this.setX(this.getX() + this.speed);
				break;
			case ROTATE_180:
				this.setY(this.getY() + this.speed);
				break;
			case ROTATE_225:
				this.setY(this.getY() + this.speed);
				this.setX(this.getX() - this.speed);
				break;
			case ROTATE_270:
				this.setX(this.getX() - this.speed);
				break;
			case ROTATE_315:
				this.setY(this.getY() - this.speed);
				this.setX(this.getX() - this.speed);
				break;
		}
	}



	@Override
	public void update () {
		this.moveFire();
	}
	
}
