package com.gdx.glex.AuxiliarFunctions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// biblioteca de funcoes facilitadoras, servem apenas para deixar o codigo mais enxuto possivel
public class RenderFunctions {

    // vai de 0f a 1f em x e y, assim facilitando a print na tela
    public static void drawInPlace(Batch batch, Texture texture, float xPos, float yPos)
    {
        batch.draw(texture, (Gdx.graphics.getWidth()/2f)*xPos*2f - texture.getWidth()/2f, (Gdx.graphics.getHeight()/2f)*yPos*2f - texture.getHeight()/2f);
    }

    // funcao auxiliar para criar o efeito parallax
    public static void drawResizedImage(Batch batch, Texture texture, float xPos)
    {
        batch.draw(texture, xPos, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
