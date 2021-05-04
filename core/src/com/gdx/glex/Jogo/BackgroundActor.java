package com.gdx.glex.Jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.glex.Assets;
import com.gdx.glex.AuxiliarFunctions.RenderFunctions;
import com.gdx.glex.Menu.MenuPrincipal.Menu;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Classe Actor, que eh basicamente como a tela deve ser mostrada no momento que
public class BackgroundActor extends Actor
{
    Assets assetsManager;
    float elapsedTime;
    int rng, velocity=500;
    Texture[] backgroundTextures = new Texture[5];
    Deque<Texture> texturesActives = new ArrayDeque<>();
    float[] texturesActivesPositions = new float[4];

    BackgroundActor(Assets assetsManager)
    {
        this.assetsManager = assetsManager;
        backgroundTextures[0] = assetsManager.manager.get("Imagens/Gameplay/Corridors1.png");
        backgroundTextures[1] = assetsManager.manager.get("Imagens/Gameplay/Corridors2.png");
        backgroundTextures[2] = assetsManager.manager.get("Imagens/Gameplay/Corridors3.png");
        backgroundTextures[3] = assetsManager.manager.get("Imagens/Gameplay/Corridors4.png");
        backgroundTextures[4] = assetsManager.manager.get("Imagens/Gameplay/wof.png");

        // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        // depois coloca um dos sprites nos texturesActives com esse indice
        // e seta a posicao da sprite em um array separado
        rng = (int) (Math.random() * 4);
        texturesActives.add(backgroundTextures[rng]);
        texturesActivesPositions[0] = 0;
        rng = (int) (Math.random() * 4);  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        texturesActives.add(backgroundTextures[rng]);
        texturesActivesPositions[1] = Gdx.graphics.getWidth();
        rng = (int) (Math.random() * 4);  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        texturesActives.add(backgroundTextures[rng]);
        texturesActivesPositions[2] = Gdx.graphics.getWidth() * 2;
        rng = (int) (Math.random() * 4);  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        texturesActives.add(backgroundTextures[rng]);
        texturesActivesPositions[3] = Gdx.graphics.getWidth() * 3;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        elapsedTime+=Gdx.graphics.getDeltaTime();
        // decidi usar deque por conta da propriedade de "rotatividade"
        if(texturesActivesPositions[0]<=-Gdx.graphics.getWidth())
            rotateDeque();
        // renderiza as imagens com uma escala maior
        RenderFunctions.drawResizedImage(batch, (Texture) texturesActives.toArray()[0], texturesActivesPositions[0]);
        RenderFunctions.drawResizedImage(batch, (Texture) texturesActives.toArray()[1], texturesActivesPositions[1]);
        RenderFunctions.drawResizedImage(batch, (Texture) texturesActives.toArray()[2], texturesActivesPositions[2]);
        RenderFunctions.drawResizedImage(batch, (Texture) texturesActives.toArray()[3], texturesActivesPositions[3]);
        // usei seno e cos so pra animar o boss de resto ta padrao
        batch.draw(backgroundTextures[4], (float) Math.cos(Math.toRadians(elapsedTime*250f))*-10-75, (float) Math.sin(Math.toRadians(elapsedTime*250f))*10, backgroundTextures[4].getWidth()*2f, Gdx.graphics.getHeight()*1.2f);
        // move as imagens para tras
        for(int i=0; i<4; i++)
            texturesActivesPositions[i] -= Gdx.graphics.getDeltaTime()*velocity;
    }

    // funcao para tirar a ultima imagem que saiu e colocar uma nova que vai entrar da direita para esquerda na tela
    // funciona basicamente como uma roleta
    private void rotateDeque()
    {
        // rotaciona as posicoes no array e coloca um novo ao fim
        for(int i=1; i<4; i++)
            texturesActivesPositions[i-1] = texturesActivesPositions[i];
        texturesActivesPositions[3] = texturesActivesPositions[2] + Gdx.graphics.getWidth();

        rng = (int) (Math.random() * 4);  // pega um numero aleatorio de 0 a 5 para ser o indice do proximo bloco
        // remove o primeiro e acrescenta ao ultimo
        texturesActives.addLast(backgroundTextures[rng]);
        texturesActives.removeFirst();
    }

}