package Screens;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Entities.Ennemy;
import Entities.EnnemySpawner;
import Entities.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.EntitySpawner;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.gui.screens.Screen;

public class InGameScreen extends Screen {
	
	public static final String NAME = "INGAME";
	private static InGameScreen instance;
	private EntitySpawner <Ennemy> ennemySpawner;

	
	
	protected InGameScreen () {
		super("INGAME");
	}
	
	public static InGameScreen getInstance () {
		if (instance == null) {
			instance = new InGameScreen();
		}
		return instance;
	}
	
	
	
	@Override
	public void prepare () {
		super.prepare();
		
		// Initialize Game
		final double screenCenterX = Game.getScreenManager().getResolution().getWidth() / 2.0;
		final double screenCenterY = Game.getScreenManager().getResolution().getHeight() / 2.0;
		Game.load("resources/maps/map_test.litidata");
		Game.loadEnvironment(new Environment(Game.getMap("map_test")));
		Game.getCamera().setFocus(screenCenterX, screenCenterY);
		Player player = new Player(new Point2D.Double(screenCenterX, screenCenterY));
		Game.getEnvironment().add(player);
		// ---------- ----------
		
		ennemySpawner = new EnnemySpawner(this.getEnnemySpawnPoints(), 5000, 1);
	}
	
	@Override
	public void render (final Graphics2D graphics2D) {
		if (Game.getEnvironment() != null) {
			Game.getEnvironment().render(graphics2D);
		}
		
		super.render(graphics2D);
	}
	
	@Override
	protected void initializeComponents () {
		
	}
	
	
	
	private List <Spawnpoint> getEnnemySpawnPoints () {
		Collection <Spawnpoint> points = Game.getEnvironment().getByTag(Spawnpoint.class, "ennemy_spawn");
		if (points.isEmpty()) {
			throw new IllegalArgumentException("No ennemy spawnpoints found on the map.");
		}
		return new ArrayList <> (points);
	}

}
