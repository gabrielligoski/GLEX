package com.gdx.glex.ParallaxEffectBGTeste;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PETeste extends ApplicationAdapter {
    private Stage stage;

    @Override
    public void create () {
        stage = new Stage(new ScreenViewport());
        Texture texture = new Texture("Imagens/menuRankingsSubTitle");

        Image imagem1 = new Image(texture);
        imagem1.setPosition(Gdx.graphics.getWidth()/3- imagem1.getWidth()/2,Gdx.graphics.getHeight()*2/3- imagem1.getHeight()/2);
        stage.addActor(imagem1);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1.0f,1.0f,1.0f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
