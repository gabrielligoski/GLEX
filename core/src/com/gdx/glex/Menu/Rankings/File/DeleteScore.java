package com.gdx.glex.Menu.Rankings.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class DeleteScore implements FileIO{

    FileHandle f = Gdx.files.local("saves.txt");
    
    @Override
    public void handleFile() {
    //Deleta o arquivo de pontuacao se ele existir
        if (f.exists())
        {
        f.delete();
        }
    }
}
