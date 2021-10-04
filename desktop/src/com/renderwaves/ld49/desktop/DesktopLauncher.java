package com.renderwaves.ld49.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.renderwaves.ld49.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.title = "Panic On The Ship";
		config.addIcon("textures/ui/ship_indicator.png", Files.FileType.Internal);
		new LwjglApplication(new Game(), config);
	}
}
