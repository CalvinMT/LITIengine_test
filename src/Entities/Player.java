package Entities;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Engine.MainLoader;
import Engine.MoreRotation;
import Screens.MenuScreen;
import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

public class Player extends Creature {
	
	private MoreRotation rotation;
	private List <FirePlayer> listFired = new ArrayList <FirePlayer> ();
	
	
	
	public Player (Point2D position) {
		this.setName("player1");
		this.setTeam(1);
		this.setLocation(position.getX() - (46/2), position.getY() - (46/2));
		this.rotation = MoreRotation.NONE;
		MainLoader.listPlayers.add(this);
		this.initialize();
	}
	
	
	
	public void initialize () {
		KeyboardEntityController <Player> keyboardController = new KeyboardEntityController<Player>(this, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		this.addController(keyboardController);
		this.setController(EntityAnimationController.class, new PlayerAnimationController(this));
		
		this.initializeInputs();
	}
	
	
	
	private void initializeInputs () {
		Input.keyboard().onKeyPressed(KeyEvent.VK_UP, (key) -> this.changeDirection(Direction.UP));
		Input.keyboard().onKeyPressed(KeyEvent.VK_DOWN, (key) -> this.changeDirection(Direction.DOWN));
		Input.keyboard().onKeyPressed(KeyEvent.VK_LEFT, (key) -> this.changeDirection(Direction.LEFT));
		Input.keyboard().onKeyPressed(KeyEvent.VK_RIGHT, (key) -> this.changeDirection(Direction.RIGHT));
		
		Input.keyboard().onKeyTyped(KeyEvent.VK_SPACE, (key) -> {
			this.fire(this);
		});
		
		Input.keyboard().onKeyTyped(KeyEvent.VK_ESCAPE, (key) -> {
			Game.getScreenManager().displayScreen(MenuScreen.NAME);
			Game.getScreenManager().getRenderComponent().setCursor(new ImageIcon("resources/cursor_white_64.png").getImage(), 0, 0);
			try {
				//TODO - Finalizes this instance of InGameScreen
				//Game.getScreenManager().getCurrentScreen()..finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		});
	}
	
	
	
	private void changeDirection (Direction direction) {
		switch (direction) {
			case UP:
				if (Input.keyboard().isPressed(KeyEvent.VK_LEFT)) {
					this.getAnimationController().playAnimation("player_top_left_32");
					this.rotation = MoreRotation.ROTATE_270;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_RIGHT)) {
					this.getAnimationController().playAnimation("player_top_right_32");
					this.rotation = MoreRotation.ROTATE_45;
				}
				else {
					this.getAnimationController().playAnimation("player_top_46");
					this.rotation = MoreRotation.NONE;
				}
				break;
			case DOWN:
				if (Input.keyboard().isPressed(KeyEvent.VK_LEFT)) {
					this.getAnimationController().playAnimation("player_bottom_left_32");
					this.rotation = MoreRotation.ROTATE_225;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_RIGHT)) {
					this.getAnimationController().playAnimation("player_bottom_right_32");
					this.rotation = MoreRotation.ROTATE_135;
				}
				else {
					this.getAnimationController().playAnimation("player_bottom_46");
					this.rotation = MoreRotation.ROTATE_180;
				}
				break;
			case LEFT:
				if (Input.keyboard().isPressed(KeyEvent.VK_UP)) {
					this.getAnimationController().playAnimation("player_top_left_32");
					this.rotation = MoreRotation.ROTATE_315;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_DOWN)) {
					this.getAnimationController().playAnimation("player_bottom_left_32");
					this.rotation = MoreRotation.ROTATE_225;
				}
				else {
					this.getAnimationController().playAnimation("player_left_46");
					this.rotation = MoreRotation.ROTATE_270;
				}
				break;
			case RIGHT:
				if (Input.keyboard().isPressed(KeyEvent.VK_UP)) {
					this.getAnimationController().playAnimation("player_top_right_32");
					this.rotation = MoreRotation.ROTATE_45;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_DOWN)) {
					this.getAnimationController().playAnimation("player_bottom_right_32");
					this.rotation = MoreRotation.ROTATE_135;
				}
				else {
					this.getAnimationController().playAnimation("player_right_46");
					this.rotation = MoreRotation.ROTATE_90;
				}
				break;
			default:
				break;
		}
		this.getAnimationController().update();
	}
	
	
	
	private void fire (Player player) {
		FirePlayer firePlayer = new FirePlayer(player);
		listFired.add(firePlayer);
		Game.getEnvironment().add(firePlayer);
	}
	
	
	
	public boolean canBeKilledBy (final IMobileEntity otherEntity) {
		if (otherEntity instanceof Player) {
			return false;
		}
		return true;
	}
	
	
	
	public MoreRotation getRotation () {
		return this.rotation;
	}
	
	public List <FirePlayer> getListFired () {
		return listFired;
	}
	
}
