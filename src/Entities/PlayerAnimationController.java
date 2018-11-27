package Entities;
import java.util.ArrayList;

import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;

public class PlayerAnimationController extends EntityAnimationController <Player> {

	public PlayerAnimationController(Player player) {
		super(player, new Animation(Spritesheet.find("player_top_46"), true), getPlayerAnimations());
	}
	
	
	
	private static Animation[] getPlayerAnimations () {
		ArrayList <Animation> animations = new ArrayList <Animation> ();
		animations.add(new Animation(Spritesheet.find("player_top_46"), true));
		animations.add(new Animation(Spritesheet.find("player_bottom_46"), true));
		animations.add(new Animation(Spritesheet.find("player_left_46"), true));
		animations.add(new Animation(Spritesheet.find("player_right_46"), true));
		animations.add(new Animation(Spritesheet.find("player_top_left_32"), true));
		animations.add(new Animation(Spritesheet.find("player_top_right_32"), true));
		animations.add(new Animation(Spritesheet.find("player_bottom_left_32"), true));
		animations.add(new Animation(Spritesheet.find("player_bottom_right_32"), true));
		return animations.toArray(new Animation[animations.size()]);
	}
	
}
