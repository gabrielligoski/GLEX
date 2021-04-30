package com.gdx.glex;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gdx.glex.Menu.MenuPrincipal.Menu;


public class GlexMain extends Game {

	@Override
	public void create () {
		this.setScreen(new Menu(this, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
	}

}












