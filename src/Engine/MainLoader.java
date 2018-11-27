package Engine;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Entities.Player;
import Screens.EndGameScreen;
import Screens.InGameScreen;
import Screens.MenuScreen;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.Resources;
import de.gurkenlabs.litiengine.gui.screens.Resolution;

public class MainLoader {
	
	public static List <Player> listPlayers = new ArrayList <Player> ();
	
	public static void main (String[] args) {
		Image imageCursor = new ImageIcon("resources/cursor_white_64.png").getImage();
		
		Resources.setEncoding(Resources.ENCODING_UTF_8);
		//Game.getRenderEngine().setBaseRenderScale((float) 5.0);
		//GuiProperties.setDefaultFont(Resources.getFont("some-font.ttf"));
		
		Game.setInfo("gameinfo.xml");
		
		Game.init();
		Game.getRenderEngine().setBaseRenderScale((float) 1.5);
		Game.getScreenManager().setResolution(Resolution.custom(1280, 720, "1280*720"));
		Game.getScreenManager().addScreen(new MenuScreen());
		Game.getScreenManager().addScreen(InGameScreen.getInstance());
		Game.getScreenManager().addScreen(new EndGameScreen());
		Game.getScreenManager().getRenderComponent().setCursor(imageCursor, 0, 0);
		Game.getScreenManager().displayScreen(MenuScreen.NAME);
		Game.start();
		
		System.out.println("Game version is: " + Game.getInfo().getVersion());
		System.out.println("Currently active screen: " + Game.getScreenManager().getCurrentScreen().getName());
		
	}
	
}
