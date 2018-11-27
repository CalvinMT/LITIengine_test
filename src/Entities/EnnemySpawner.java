package Entities;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.EntitySpawner;

public class EnnemySpawner extends EntitySpawner <Ennemy> {

	public EnnemySpawner (List<Spawnpoint> spawnpoints, int interval, int amount) {
		super(Game.getEnvironment(), Game.getLoop(), spawnpoints, interval, amount);
		this.setSpawnMode(SpawnMode.ONERANDOMSPAWNPOINT);
	}
	
	
	/*
	private Point2D getRandomSpawnLocation () {
		Random r = new Random();
		Spawnpoint spawnPoints = this.getSpawnPoints().get(r.nextInt(this.getSpawnPoints().size()-1));
		Point2D resultLocation = spawnPoints.getLocation();
		resultLocation.setLocation(spawnPoints.getX()+(spawnPoints.getWidth()*r.nextDouble()), spawnPoints.getY()+(spawnPoints.getHeight()*r.nextDouble()));
		return resultLocation;
	}
	*/
	
	
	@Override
	public Ennemy createNew () {
		return new Ennemy();
	}
	
}
