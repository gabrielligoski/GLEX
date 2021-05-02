package com.gdx.glex.Jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.glex.Assets;
import com.gdx.glex.AuxiliarFunctions.RenderFunctions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Stream;

// Classe Actor, que eh basicamente como a tela deve ser mostrada no momento que
public class BackgroundActor extends Actor
{
    Assets assetsManager;
    float elapsedTime;
    int rng, velocity=500;
    Texture[] backgroundTextures = new Texture[4];
    Deque<Texture> texturesActives = new ArrayDeque<>();
    float[] texturesActivesPositions = new float[4];

    BackgroundActor(Assets assetsManager)
    {
        this.assetsManager = assetsManager;
        backgroundTextures[0] = assetsManager.manager.get("Imagens/Gameplay/Camada 2.png");
        backgroundTextures[1] = assetsManager.manager.get("Imagens/Gameplay/Camada 3.png");
        backgroundTextures[2] = assetsManager.manager.get("Imagens/Gameplay/Camada 4.png");
        backgroundTextures[3] = assetsManager.manager.get("Imagens/Gameplay/Camada 5.png");
        rng = (int)(Math.random()*((3-0)+1))+0;  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        texturesActives.add(backgroundTextures[rng]);
        texturesActivesPositions[0] = 0;
        rng = (int)(Math.random()*((3-0)+1))+0;  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        texturesActives.add(backgroundTextures[rng]);
        texturesActivesPositions[1] = ((Texture) texturesActives.toArray()[0]).getWidth() * 6.75f;
        rng = (int)(Math.random()*((3-0)+1))+0;  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        texturesActives.add(backgroundTextures[rng]);
        texturesActivesPositions[2] = ((Texture) texturesActives.toArray()[1]).getWidth() * 6.75f + texturesActivesPositions[1];
        rng = (int)(Math.random()*((3-0)+1))+0;  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        texturesActives.add(backgroundTextures[rng]);
        texturesActivesPositions[3] = ((Texture) texturesActives.toArray()[2]).getWidth() * 6.75f + texturesActivesPositions[1] + texturesActivesPositions[2];
    }

    //@android.annotation.SuppressLint("NewApi")
    @Override
    public void draw(Batch batch, float parentAlpha)
    {

        //elapsedTime -= Gdx.graphics.getDeltaTime()*velocity;
        //rng = (int)(Math.random()*((3-0)+1))+0; // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        if(texturesActivesPositions[0]<=-Gdx.graphics.getWidth()/4)
            rotateDeque();
        RenderFunctions.drawResizedImage(batch, (Texture) texturesActives.toArray()[0], texturesActivesPositions[0]);
        RenderFunctions.drawResizedImage(batch, (Texture) texturesActives.toArray()[1], texturesActivesPositions[1]);
        RenderFunctions.drawResizedImage(batch, (Texture) texturesActives.toArray()[2], texturesActivesPositions[2]);
        RenderFunctions.drawResizedImage(batch, (Texture) texturesActives.toArray()[3], texturesActivesPositions[3]);
        for(int i=0; i<4; i++)
            texturesActivesPositions[i] -= Gdx.graphics.getDeltaTime()*velocity;
//        batch.draw((Texture) animation.getKeyFrame(elapsedTime, true),0,0);
//        batch.draw(texture, (Gdx.graphics.getWidth()/2f)*xPos*2f - texture.getWidth()/2f, (Gdx.graphics.getHeight()/2f)*yPos*2f - texture.getHeight()/2f);
//        RenderFunctions.drawInPlace(batch, text[0], 0.5f, 0.85f);
//        RenderFunctions.drawInPlace(batch, selectedButtonId==0? textSelected[0]:text[1], 0.5f, 0.65f);
//        RenderFunctions.drawInPlace(batch, selectedButtonId==1? textSelected[1]:text[2], 0.5f, 0.45f);
//        RenderFunctions.drawInPlace(batch, selectedButtonId==2? textSelected[2]:text[3], 0.5f, 0.25f);
    }

    private void rotateDeque()
    {
        for(int i=1; i<4; i++)
            texturesActivesPositions[i-1] = texturesActivesPositions[i];
        texturesActivesPositions[3] = texturesActivesPositions[2] + texturesActives.peekLast().getWidth() * 6.75f;

        rng = (int)(Math.random()*((3-0)+1))+0;  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        texturesActives.addLast(backgroundTextures[rng]);
        texturesActives.removeFirst();
    }

}