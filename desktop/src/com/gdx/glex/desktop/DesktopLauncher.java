package com.gdx.glex.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.glex.GlexMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Game";
		config.width = 1940;
		config.height = 1080;

		// fullscreen
		config.fullscreen = true;
		// vSync
		config.vSyncEnabled = true;

		config.resizable=false;

		new LwjglApplication(new GlexMain(), config);
	}
}
