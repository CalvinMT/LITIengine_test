package Entities;
import java.util.ArrayList;

import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;

public class FirePlayerAnimationController extends EntityAnimationController <FirePlayer> {
	
	public FirePlayerAnimationController (FirePlayer firePlayer) {
		super(firePlayer, new Animation(Spritesheet.find("fire_player_16"), true), getFirePlayerAnimations());
	}
	
	
	
	private static Animation[] getFirePlayerAnimations () {
		ArrayList <Animation> animations = new ArrayList <Animation> ();
		animations.add(new Animation(Spritesheet.find("fire_player_top_16"), true));
		animations.add(new Animation(Spritesheet.find("fire_player_bottom_16"), true));
		animations.add(new Animation(Spritesheet.find("fire_player_left_16"), true));
		animations.add(new Animation(Spritesheet.find("fire_player_right_16"), true));
		animations.add(new Animation(Spritesheet.find("fire_player_top_left_16"), true));
		animations.add(new Animation(Spritesheet.find("fire_player_top_right_16"), true));
		animations.add(new Animation(Spritesheet.find("fire_player_bottom_left_16"), true));
		animations.add(new Animation(Spritesheet.find("fire_player_bottom_right_16"), true));
		return animations.toArray(new Animation[animations.size()]);
	}
	
}
