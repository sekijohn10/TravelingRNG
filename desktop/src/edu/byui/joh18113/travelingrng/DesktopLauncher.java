package edu.byui.joh18113.travelingrng;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import edu.byui.joh18113.travelingrng.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("TravelingRNG");
		config.setWindowedMode(1480, 1000);
		new Lwjgl3Application(new Main(), config);
	}
}
