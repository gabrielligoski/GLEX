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
import com.gdx.glex.Menu.MenuPrincipal.Menu;

public class Rankings extends MenuPage implements Screen {

    private static String name = "menu Ranking"; // token de indentificacao para o Asset Loader

    private Texture[]  text, textSelected;
    private Animation animation;
    private float elapsedTime;

    // muda o Id do botao selecionado na tela atual
    @Override
    public void changeSelectedButtonId(int num)
    {
        if((selectedButtonId+num)>=0 && (selectedButtonId+num)<=2)
            selectedButtonId+=num;
    }

    @Override
    public void callSelectedButton()
    {
        if(selectedButtonId==2)
            System.exit(0);
    }

    // Classe Actor, que eh basicamente como a tela deve ser mostrada no momento que
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


    // Construtor
    public Rankings(Game g, int viewWidth, int viewHeight)
    {
        super(g, viewWidth, viewHeight, Rankings.name);
    }

    // Chamada executada 1 vez ao terminar de carregar o Menu na tela
    public void create () {
        // carrega imagens e gif's da memoria
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Gifs/MenuBackgroundGif.gif").read());

        text =
                new Texture[]{
                        assetsManager.manager.get("Imagens/menuRankingsTitle.png"),
                        assetsManager.manager.get("Imagens/menuRankingsSubTitle.png"),
                        //todo Carrega o próprio ranking aqui

                        assetsManager.manager.get("Imagens/exitButton.png")
                };
        textSelected =
                new Texture[]{
                        assetsManager.manager.get("Imagens/exitSelectedButton.png")
                };

        mainStage.addActor(new RankingsActor());

        // Seta o InputHandler para ser utilizado nesta tela
        Gdx.input.setInputProcessor(new InputHandler( this));
    }
}
