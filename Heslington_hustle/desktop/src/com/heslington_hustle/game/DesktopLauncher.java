package com.heslington_hustle.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//config.setForegroundFPS(60);
		config.setTitle("Heslington Hustle");
		config.setWindowedMode(800, 800);
		config.setForegroundFPS(60);
		config.setResizable(false);
		new Lwjgl3Application(new HeslingtonHustle(), config);
	}
}
