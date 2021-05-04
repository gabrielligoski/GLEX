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

public class Rankings extends MenuPage implements Screen {
    
    //Atts
    private Animation animation;
    private Texture hud, cursor, selected;
    private float elapsedtime;
    
    public Rankings(Game g, int viewWidth, int viewHeight){
        super(g, viewWidth, viewHeight, Rankings.class);
    }

    public Rankings(Game g, int viewWidth, int viewHeight, Player player){
        super(g, viewWidth, viewHeight, Rankings.class);
        FileHandle file = Gdx.files.local("saves.txt");
        file.writeString(player.getName() + " - " + player.getScore() + " - " + player.getLastGame(), true);
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
        }
    }
     
    
    @Override
    public void create() {
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
        if (selectedButtonId == 0){
            game.setScreen(new Menu(game, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        }
       /* else

        }*/  
    }
}
