package com.gdx.glex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
        }
    }

    private void loadMenu()
    {
        manager.load("Imagens/menuTitle.png", Texture.class);
        manager.load("Imagens/startText.png", Texture.class);
        manager.load("Imagens/rankingsText.png", Texture.class);
        manager.load("Imagens/exitText.png", Texture.class);

        manager.load("Imagens/startBlue.png", Texture.class);
        manager.load("Imagens/rankingsBlue.png", Texture.class);
        manager.load("Imagens/exitBlue.png", Texture.class);
    }

    private void loadRankings()
    {
//        manager.load("Imagens/menuTitle.png", TextureAtlas.class);
//        manager.load("Imagens/startText.png", TextureAtlas.class);
//        manager.load("Imagens/rankingsText.png", TextureAtlas.class);
//        manager.load("Imagens/exitText.png", TextureAtlas.class);
//
//        manager.load("Imagens/startBlue.png", TextureAtlas.class);
//        manager.load("Imagens/rankingsBlue.png", TextureAtlas.class);
//        manager.load("Imagens/exitBlue.png", TextureAtlas.class);
    }
}
