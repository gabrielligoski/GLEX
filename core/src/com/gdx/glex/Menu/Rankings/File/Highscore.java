package com.gdx.glex.Menu.Rankings.File;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.*;
import java.nio.file.Files;

public class Highscore  implements FileIO{
    
    private int score;
    
    public Highscore(int score)
    {
        this.score = score;
    }
    
    File f = new File("score.bin");
    
    @Override
    public void handleFile() {
        //verifica se o arquivo existe
        if (f.exists())
        {
            
        }
        else //cria o arquivo e escreve na primeira linha dele
        {
            try 
            {
                f.createNewFile();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Highscore.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
    }
}
