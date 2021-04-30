package com.gdx.glex;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    public AssetManager manager;

    public void load(Object type){
        if(manager==null)
            manager = new AssetManager();
        else
            manager.clear();
        switch (type.getClass().toString())
        {
            case "class com.gdx.glex.Menu.Menu":
                loadMenu();
        }
    }

    private void loadMenu()
    {
        manager.load("Imagens/menuWallpaper.png", TextureAtlas.class);
        manager.load("Animations/unnamed.png", TextureAtlas.class);
    }
}
