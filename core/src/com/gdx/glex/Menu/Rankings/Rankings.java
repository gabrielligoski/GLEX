package com.gdx.glex.Menu.Rankings;

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
import com.gdx.glex.Menu.MenuPage;

public class Rankings extends MenuPage implements Screen {

    // Work in progress currently only ctrl-v'ed

    static String name = "rankings";

    private Texture[]  text, textSelected;
    private Animation animation;
    private float elapsedTime;

    class RankingsActor extends Actor
    {
        @Override
        public void draw(Batch batch, float parentAlpha)
        {
            elapsedTime += Gdx.graphics.getDeltaTime();
            batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true),0,0);
            RenderFunctions.drawInPlace(batch, text[0], 0.5f, 0.85f);
            RenderFunctions.drawInPlace(batch, selectedButtonId==0? textSelected[0]:text[1], 0.5f, 0.65f);
            RenderFunctions.drawInPlace(batch, selectedButtonId==1? textSelected[1]:text[2], 0.5f, 0.45f);
            RenderFunctions.drawInPlace(batch, selectedButtonId==2? textSelected[2]:text[3], 0.5f, 0.25f);
        }
    }

    public Rankings(Game g, int viewWidth, int viewHeight)
    {
        super(g, viewWidth, viewHeight, Rankings.name);
        create();
    }

    public void create () {
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Gifs/MenuBackgroundGif.gif").read());

        text =
                new Texture[]{
                        new Texture(Gdx.files.internal("Imagens/menuTitle.png")),
                        new Texture(Gdx.files.internal("Imagens/startText.png")),
                        new Texture(Gdx.files.internal("Imagens/rankingsText.png")),
                        new Texture(Gdx.files.internal("Imagens/exitText.png"))
                };
        textSelected =
                new Texture[]{
                        new Texture(Gdx.files.internal("Imagens/startBlue.png")),
                        new Texture(Gdx.files.internal("Imagens/rankingsBlue.png")),
                        new Texture(Gdx.files.internal("Imagens/exitBlue.png"))
                };

        mainStage.addActor(new RankingsActor());
        Gdx.input.setInputProcessor(new InputHandler(this));
    }
}
