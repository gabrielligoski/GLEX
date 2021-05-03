package com.gdx.glex.Menu.Rankings.File;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowScore implements FileIO{
    
    private File f = new File("score.bin");
    public Table table = new Table();
    
    @Override
    public void handleFile() {
    //Le o arquivo de pontuacao
        if (f.exists())
        { 
            try 
            {
                FileInputStream in = new FileInputStream("score.bin");
                int[] all = new int[in.available() / 4];
                for(int i = 0; i < all.length; i++)
                {
                    byte[] d = new byte[4];
                    in.read(d);
                    System.out.println(d);                                        //DELETAR
                    all[i] = (4096 * d[0]) + (256 * d[1]) + (16 * d[2]) + d[3];
                }
                Label[] s = new Label[all.length];
                table.setFillParent(true);
                for (int i = 0; i < s.length; ++i)
                {
                    s[i] = new Label(String.format("%08d", all[i]), new LabelStyle(new BitmapFont(), Color.WHITE));
                    table.add(s[i]).expandY().padLeft(1f);
                }
                
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(ShowScore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ShowScore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
