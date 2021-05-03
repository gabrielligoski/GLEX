package com.gdx.glex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;

// Classe que carrega e gerencia as imagens carregadas na memoria
public class Assets {
    public AssetManager manager;

    public void load(String type){
        if(manager==null)
            manager = new AssetManager();
        else
            manager.clear();
        switch (type)
        {
            case "menu":
                loadMenu();
                break;
            case "rankings":
                loadRankings();
                break;
            case "jogo":
                loadBackgroundGameplay();
                loadGameplay();
                break;
        }
    }

    private void loadMenu()
    {
        manager.load("Imagens/Menu/menuTitle.png", Texture.class);
        manager.load("Imagens/Menu/startText.png", Texture.class);
        manager.load("Imagens/Menu/rankingsText.png", Texture.class);
        manager.load("Imagens/Menu/exitText.png", Texture.class);

        manager.load("Imagens/Menu/startBlue.png", Texture.class);
        manager.load("Imagens/Menu/rankingsBlue.png", Texture.class);
        manager.load("Imagens/Menu/exitBlue.png", Texture.class);
    }

    private void loadRankings()
    {
        manager.load("Imagens/Rankings/cursor.png", Texture.class);
        manager.load("Imagens/Rankings/rankingsFinal.png", Texture.class);
    }

    private void loadBackgroundGameplay()
    {
        manager.load("Imagens/Gameplay/Corridors1.png", Texture.class);
        manager.load("Imagens/Gameplay/Corridors2.png", Texture.class);
        manager.load("Imagens/Gameplay/Corridors3.png", Texture.class);
        manager.load("Imagens/Gameplay/Corridors4.png", Texture.class);
        manager.load("Imagens/Gameplay/wof.png", Texture.class);
    }

    private void loadGameplay()
    {
        manager.load("Animations/playerAttack.png", Texture.class);
        manager.load("Animations/playerRun.png", Texture.class);
        manager.load("Animations/monstro.png", Texture.class);
        manager.load("Animations/ghost.png", Texture.class);
        manager.load("Imagens/Gameplay/deathMessage.png", Texture.class);
        manager.load("Fonts/OldFont.fnt", BitmapFont.class); //or use alex answer to use custom font
    }
}
