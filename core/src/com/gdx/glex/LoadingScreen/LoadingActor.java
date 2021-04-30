package com.gdx.glex.LoadingScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.glex.AuxiliarFunctions.AnimationFunctions;
import com.gdx.glex.GifDecoder;

public class LoadingActor extends Actor
{
    private float elapsedTime;
    private Animation dog, bonfire;

    public LoadingActor()
    {
        dog = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Gifs/loadingDog.gif").read());
        bonfire = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Gifs/loadingBonfire.gif").read());
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        elapsedTime += Gdx.graphics.getDeltaTime();
        AnimationFunctions.animateInPlace(batch, bonfire, elapsedTime, 0.70f, 0.075f, true);
        AnimationFunctions.animateInPlace(batch, dog, elapsedTime, 0.85f, 0.075f, true);
    }
}