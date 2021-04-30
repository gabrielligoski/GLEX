package com.gdx.glex.AuxiliarFunctions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// biblioteca de funcoes facilitadoras, servem apenas para deixar o codigo mais enxuto possivel
public class AnimationFunctions {

    // a animcao tem q ter numero par de frames, e tem que utilizar 2 colunas
    public static Animation png2Animation(String path, int width, int Height, int padding, int frameNumber, float fps, int offset)
    {
        Texture pngFile = new Texture(Gdx.files.internal(path));
        TextureRegion[][] tmpFrames = TextureRegion.split(pngFile, width - padding,Height - padding);

        TextureRegion[] animationFrames = new TextureRegion[frameNumber];
        int index = 0;

        for(int i=offset; i<frameNumber/2; i++)
            for(int j = 0; j<2;j++)
                animationFrames[index++] = tmpFrames[i][j];

        return new Animation(1f/fps, animationFrames);
    }

    //ref: batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true),0,0);
    // vai de 0f a 1f em x e y, assim facilitando a print na tela
    public static void animateInPlace(Batch batch, Animation animation, float elapsedTime, float xPos, float yPos, boolean looping)
    {
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, looping),
                (Gdx.graphics.getWidth()/2f)*xPos*2f - ((TextureRegion) animation.getKeyFrame(0)).getRegionWidth()/2f,
                (Gdx.graphics.getWidth()/2f)*yPos*2f - ((TextureRegion) animation.getKeyFrame(0)).getRegionHeight()/2f);
    }

    public static void animateInPlace(Batch batch, Animation animation, float elapsedTime, float xPos, float yPos)
    {
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, false),
                (Gdx.graphics.getWidth()/2f)*xPos*2f - ((TextureRegion) animation.getKeyFrame(0)).getRegionWidth()/2f,
                (Gdx.graphics.getWidth()/2f)*yPos*2f - ((TextureRegion) animation.getKeyFrame(0)).getRegionHeight()/2f);
    }

}
