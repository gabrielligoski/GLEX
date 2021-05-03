package com.gdx.glex.Menu.Rankings.File;

import java.io.*;

public class DeleteScore implements FileIO{

    File f = new File("score.bin");
    
    @Override
    public void handleFile() {
    //Deleta o arquivo de pontuacao se ele existir
        if (f.exists())
        {
        f.delete();   
        }
    }
}
