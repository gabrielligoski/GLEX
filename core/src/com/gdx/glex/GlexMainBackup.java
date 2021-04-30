package com.gdx.glex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GlexMainBackup extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture menuBackground, buttonGif;
	private Sprite sprite;
	private TextureRegion[] animationFrames;
	private Animation animation;
	private float elapsedTime;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		menuBackground = new Texture(Gdx.files.internal("Imagens/menuWallpaper.png"));
		menuBackground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // melhora qualidade acho...
		System.out.println(this.getClass().toString());


		sprite = new Sprite(menuBackground);
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}


	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor( 0,0,0,1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		batch.begin();
		batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, false),0,0);
		//batch.draw(menuBackground, Gdx.graphics.getWidth()/2 - menuBackground.getWidth()/2, Gdx.graphics.getHeight()/2 - menuBackground.getHeight()/2);
		//batch.draw(sprite.getTexture(), 0, 0, sprite.getWidth(), sprite.getHeight());
		batch.end();
	}
}












