package Entities;
import java.util.ArrayList;

import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;

public class EnnemyAnimationController extends EntityAnimationController <Ennemy> {

	public EnnemyAnimationController(Ennemy ennemy) {
		super(ennemy, new Animation(Spritesheet.find("ennemy1_32"), true), getEnnemyAnimations());
	}
	
	
	
	private static Animation[] getEnnemyAnimations () {
		ArrayList <Animation> animations = new ArrayList <Animation> ();
		animations.add(new Animation(Spritesheet.find("ennemy1_32"), true));
		return animations.toArray(new Animation[animations.size()]);
	}

}
