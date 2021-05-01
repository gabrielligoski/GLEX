package com.gdx.glex.Menu.MenuPrincipal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gdx.glex.Assets;
import com.gdx.glex.AuxiliarFunctions.RenderFunctions;
import com.gdx.glex.GifDecoder;
import com.gdx.glex.LoadingScreen.LoadingActor;
import com.gdx.glex.Menu.InputHandler;
import com.gdx.glex.Menu.MenuPage;

public class Menu extends MenuPage implements Screen {

    private static String name = "menu"; // token de indentificacao para o Asset Loader

    private Texture[]  text, textSelected;
    private Animation animation;
    private float elapsedTime;

    // Classe Actor, que eh basicamente como a tela deve ser mostrada no momento que
    class MenuActor extends Actor
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

    // Construtor
    public Menu(Game g, int viewWidth, int viewHeight)
    {
        super(g, viewWidth, viewHeight, Menu.name);
    }

    // Chamada executada 1 vez ao terminar de carregar o Menu na tela
    public void create () {
        // carrega imagens e gif's da memoria
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Gifs/MenuBackgroundGif.gif").read());

        text =
                new Texture[]{
                        assetsManager.manager.get("Imagens/menuTitle.png"),
                        assetsManager.manager.get("Imagens/startText.png"),
                        assetsManager.manager.get("Imagens/rankingsText.png"),
                        assetsManager.manager.get("Imagens/exitText.png")
                };
        textSelected =
                new Texture[]{
                        assetsManager.manager.get("Imagens/startBlue.png"),
                        assetsManager.manager.get("Imagens/rankingsBlue.png"),
                        assetsManager.manager.get("Imagens/exitBlue.png")
                };

        mainStage.addActor(new MenuActor());

        // Seta o InputHandler para ser utilizado nesta tela
        Gdx.input.setInputProcessor(new InputHandler( this));
    }
}