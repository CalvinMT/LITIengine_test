package Entities;

import Engine.MainLoader;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.pathfinding.EntityNavigator;
import de.gurkenlabs.litiengine.pathfinding.astar.AStarPathFinder;
import de.gurkenlabs.litiengine.physics.MovementController;

public class EnnemyController extends MovementController <Ennemy> {
	
	private Player closestPlayer = null;
	private EntityNavigator navigator;
	
	
	
	public EnnemyController (Ennemy ennemy) {
		super(ennemy);
		this.navigator = new EntityNavigator(ennemy, new AStarPathFinder(Game.getEnvironment().getMap()));
	}
	
	
	
	private Player getClosestPlayer () {
		if (! MainLoader.listPlayers.isEmpty()) {
			closestPlayer = MainLoader.listPlayers.get(0);
			MainLoader.listPlayers.stream().forEach(player -> {
				if (Math.abs(this.getEntity().getX()-player.getX()) < Math.abs(this.getEntity().getX()-closestPlayer.getX())  &&  Math.abs(this.getEntity().getY()-player.getY()) < Math.abs(this.getEntity().getY()-closestPlayer.getY())) {
					closestPlayer = player;
				}
			});
		}
		return closestPlayer;
	}
	
	
	
	@Override
	public void update() {
		super.update();
		
		//this.getEntity().getAnimationController().playAnimation("ennemy1_32");
		
		// Move towards closest player
		this.getEntity().setTarget(this.getClosestPlayer());
		this.getEntity().setMoveDestination(this.getEntity().getTarget().getCenter());
		this.navigator.navigate(this.getEntity().getMoveDestination());
	}
	
}
