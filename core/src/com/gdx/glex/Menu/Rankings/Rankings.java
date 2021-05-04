package com.gdx.glex.Menu.Rankings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.glex.AuxiliarFunctions.RenderFunctions;
import com.gdx.glex.GifDecoder;
import com.gdx.glex.Menu.MenuPage;
import com.gdx.glex.Menu.MenuPrincipal.Menu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.util.Scanner;

public class Rankings extends MenuPage implements Screen {
    private Animation animation;
    private Texture hud, cursor, selected;
    private float elapsedtime;
    private BitmapFont font;

    private Player[] playersRanking = new Player[5];
    
    public Rankings(Game g, int viewWidth, int viewHeight){
        super(g, viewWidth, viewHeight, Rankings.class);
    }

    public Rankings(Game g, int viewWidth, int viewHeight, Player player){
        super(g, viewWidth, viewHeight, Rankings.class);

        Scanner save = new Scanner(Gdx.files.local("saves.txt").read());
        boolean wasWritten=true;
        String[] temp = {save.nextLine(), save.nextLine(), save.nextLine(), save.nextLine(), save.nextLine()};
        String tempFinal = "";
        for(int i=0; i<5; i++) {
            if ( Integer.parseInt(temp[i].split(" - ")[1]) < player.getScore() && wasWritten) {
                temp[i] = player.getName() + " - " + player.getScore() + " - " + player.getLastGame();
                wasWritten=false;
            }
            tempFinal+=temp[i]+"\n";
        }
        FileHandle file = Gdx.files.local("saves.txt");
        file.writeString(tempFinal, false);
    }

     //Ator da cena de ranking
    class RankingsActor extends Actor
    {
        @Override
        public void draw(Batch batch, float parentAlpha)
        {
            elapsedtime += Gdx.graphics.getDeltaTime();
            batch.draw((TextureRegion) animation.getKeyFrame(elapsedtime, true),0,0);
            RenderFunctions.drawInPlace(batch, hud, 0.5f, 0.5f);
            if (selectedButtonId == 0)
                RenderFunctions.drawInPlace(batch, cursor, 0.45f, 0.08f);
            else
                RenderFunctions.drawInPlace(batch, cursor, 0.7f, 0.08f);
            for(int i=0; i<5;i++)
                font.draw(batch, "Nome: " + playersRanking[i].getName() + " - " + playersRanking[i].getScore() + " - " + playersRanking[i].getLastGame(),
                        Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/3f+Gdx.graphics.getHeight()*i/10f);
        }
    }
     
    
    @Override
    public void create() throws FileNotFoundException {
        font = assetsManager.manager.get("Fonts/pixel.fnt");

        Scanner save = new Scanner(Gdx.files.local("saves.txt").read());
        int i =0;
        while (save.hasNextLine()) {
            String temp = save.nextLine();
            playersRanking[i++] = new Player(temp.split(" - ")[0], Integer.parseInt(temp.split(" - ")[1]));
        }

        //Background
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Gifs/MenuBackgroundGif.gif").read());
        
        //Hud do ranking
        hud = assetsManager.manager.get("Imagens/Rankings/rankingsFinal.png");
        
        //Cursor que percorre o ranking
        cursor = assetsManager.manager.get(("Imagens/Rankings/cursor.png"));

        selected = assetsManager.manager.get(("Imagens/Rankings/selectedRanking.png"));

        //Cria Ator a ser mostrado na tela
        mainStage.addActor(new RankingsActor());
        Gdx.input.setInputProcessor(new com.gdx.glex.Menu.InputHandler(this, this));

    }
    
    @Override
    public void changeSelectedButtonId(int num) {
       selectedButtonId =  selectedButtonId == 0? 1: 0;
    }

    @Override
    public void callSelectedButton() {
        if (selectedButtonId == 0)
            game.setScreen(new Menu(game, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
    }
}
