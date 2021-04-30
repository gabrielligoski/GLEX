package com.gdx.glex.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gdx.glex.AuxiliarFunctions.RenderFunctions;
import com.gdx.glex.GifDecoder;
import com.gdx.glex.Menu.InputHandler;

public abstract class MenuPage implements Screen{

    protected Game game;
    protected Stage mainStage;
    protected final int viewWidth;
    protected final int viewHeight;
    protected int selectedButtonId=0;

    public void changeSelectedButtonId(int num)
    {
        if((selectedButtonId+num)>=0 && (selectedButtonId+num)<=2)
            selectedButtonId+=num;
    }

    public MenuPage(Game g, int viewWidth, int viewHeight)
    {
        game = g;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        mainStage = new Stage(new FitViewport(viewWidth, viewHeight));
    }

    public abstract void create();

    @Override
    public void render(float dt)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
        mainStage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}












