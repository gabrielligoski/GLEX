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
import com.gdx.glex.Assets;
import com.gdx.glex.AuxiliarFunctions.RenderFunctions;
import com.gdx.glex.GifDecoder;
import com.gdx.glex.LoadingScreen.LoadingActor;
import com.gdx.glex.Menu.InputHandler;

// Classe abstrata de Menu que especifica como as telas de menu devem ser implementadas
public abstract class MenuPage implements Screen{

    protected Game game;
    protected Stage mainStage;
    protected final int viewWidth;
    protected final int viewHeight;
    protected int selectedButtonId=0;
    protected Assets assetsManager;

    private boolean isFinished;

    // muda o Id do botao selecionado na tela atual
    public abstract void changeSelectedButtonId(int num);

    public abstract void callSelectedButton();

    // recebe o objeto jogo(responsavel por delegar as telas), o tamanho da tela e o tipo de tela
    public MenuPage(Game g, int viewWidth, int viewHeight, String type)
    {
        game = g;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        // Cria um Stage que pode ser pensado como uma mascara dentro de um Actor que eh oq vai ser mostrado na tela
        // e.g. podemos ter um Stage principal que mostra as imagens do jogo em si e outro para mostrar
        // a UI quando pausado o jogo ou inventario
        mainStage = new Stage(new FitViewport(viewWidth, viewHeight));

        // inicia carregamento das imagens
        assetsManager = new Assets();
        assetsManager.load(type);

        // Mostra a loadingScreen enquanto nao termina de carregar as imagens
        mainStage.addActor(new LoadingActor());
    }

    // pelo ciclo de execucao o create eh apos os construtores a primeira classe a ser executada uma unica vez
    public abstract void create();

    // Chamado enquanto nao termina de carregar os assets
    private void update(float dt)
    {
        if(assetsManager.manager.update())
        {
            isFinished=true;
            mainStage.clear();
            create();
        }
    }

    // Chamada executada todo frame, ou seja fps depende disto
    @Override
    public void render(float dt)
    {
        // diz a cor de fundo da tela e da um clear nela
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // checa se ja terminou de carregar os assets
        if (!isFinished)
            update(dt);

        // printa o Actor que estiver em mainStage na tela
        mainStage.draw();
    }

    // Metodos que necessitam ser implementados por Screen porem nao relevantes por enquanto

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

    // chamado quando a tela eh fechada
    @Override
    public void dispose() {
        mainStage.dispose();
    }

}












