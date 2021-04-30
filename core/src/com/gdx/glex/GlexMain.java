package com.gdx.glex;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gdx.glex.Menu.MenuPrincipal.Menu;


public class GlexMain extends Game {

	// Classe 'Main' da framework, ela eh chamada quando inicia a applicacao pelas classes 'plataforma'Launcher que especificam coisas relativas a plataforma.
	@Override
	public void create () {
		// eu estou dizendo para setar a tela do Game(a classe que GlexMain herda) para ser o Menu(que herda Screen).
		this.setScreen(new Menu(this, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
	}

}












