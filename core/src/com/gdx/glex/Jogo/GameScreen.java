package com.gdx.glex.Jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gdx.glex.Assets;
import com.gdx.glex.AuxiliarFunctions.RenderFunctions;
import com.gdx.glex.LoadingScreen.LoadingActor;

public class GameScreen implements Screen {
    private Game game;
    private Stage mainStage, backgroundStage;
    private final int viewWidth;
    private final int viewHeight;
    private int selectedButtonId=0;
    private Assets assetsManager;
    private float elapsedTime;
    private GameplayActor mainActor;
    private Music music;

    private boolean isFinished;

    private static String name = "jogo"; // token de indentificacao para o Asset Loader

    // recebe o objeto jogo(responsavel por delegar as telas), o tamanho da tela e o tipo de tela
    public GameScreen(Game g, int viewWidth, int viewHeight)
    {
        game = g;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        // Cria um Stage que pode ser pensado como uma mascara dentro de um Actor que eh oq vai ser mostrado na tela
        // e.g. podemos ter um Stage principal que mostra as imagens do jogo em si e outro para mostrar
        // a UI quando pausado o jogo ou inventario
        mainStage = new Stage(new FitViewport(viewWidth, viewHeight));
        backgroundStage = new Stage(new FitViewport(viewWidth, viewHeight));

        // inicia carregamento das imagens
        assetsManager = new Assets();
        assetsManager.load(name);

        // Mostra a loadingScreen enquanto nao termina de carregar as imagens
        mainStage.addActor(new LoadingActor());
    }

    // pelo ciclo de execucao o create eh apos os construtores a primeira classe a ser executada uma unica vez
    public void create() {
        mainStage.addActor(mainActor);
        backgroundStage.addActor(new BackgroundActor(assetsManager));
        music = assetsManager.manager.get("Sounds/gameplayMusic.mp3");
        music.setLooping(true);
        music.play();
    }

    // Chamado enquanto nao termina de carregar os assets
    private void update(float dt)
    {
        if(assetsManager.manager.update())
        {
            isFinished=true;
            mainStage.clear();
            mainActor =  new GameplayActor(assetsManager);
            // Seta o input handler aqui
            Gdx.input.setInputProcessor(new InputHandler(mainActor));
            create();
        }
    }

    // Chamada executada todos frame, ou seja fps depende disto
    @Override
    public void render(float dt)
    {
        // diz a cor de fundo da tela e da um clear nela
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // checa se ja terminou de carregar os assets
        if (!isFinished)
            update(dt);

        // printa os Actors
        backgroundStage.draw();
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
        music.dispose();
    }
}
