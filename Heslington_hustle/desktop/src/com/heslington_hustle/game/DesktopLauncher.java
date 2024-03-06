package com.heslington_hustle.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.heslington_hustle.game.Heslington_hustle;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//config.setForegroundFPS(60);
		config.setTitle("Heslington Hustle");
		config.setWindowedMode(1200, 1200);
		new Lwjgl3Application(new Heslington_hustle(), config);
	}
}
