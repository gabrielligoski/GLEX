package com.gdx.glex;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;


// Como utilizar a class:
// game.setScreen(new ScreenClass());

public abstract class AbstractScene implements Screen
{
    protected Game game;
    protected Stage mainStage;
    protected Stage uiStage;
    public final int viewWidth;
    public final int viewHeight;
    protected boolean paused;

    public AbstractScene(Game g, int viewWidth, int viewHeight)
    {
        game = g;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        mainStage = new Stage(new FitViewport(viewWidth, viewHeight));
        uiStage = new Stage(new FitViewport(viewWidth, viewHeight));

        create();
    }

    public abstract void create();

    public abstract void update(float dt);

    @Override
    public void render(float dt)
    {
        uiStage.act(dt);
        if (!paused)
        {
            mainStage.act(dt);
            //update(dt);
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
        mainStage.draw();
        uiStage.draw();
    }

}