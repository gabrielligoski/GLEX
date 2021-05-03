package com.gdx.glex.AuxiliarFunctions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.glex.Jogo.GameplayActor;

import java.util.ArrayList;

public class GameplayFunctions {

    public static void spawnMonster(ArrayList<Float> getMonstersPositions, ArrayList<Float> monsterAnimationArray, ArrayList<Boolean> isMonsterDead)
    {
        // pega um numero dentre 0 e 100 aleatoriamente se for menor que 15 spawna um zumbi
        int rng = (int) (Math.random() * 10000/3f);

        if(rng<15) {
            getMonstersPositions.add(Gdx.graphics.getWidth()+100f);
            monsterAnimationArray.add(0f);
            isMonsterDead.add(false);
        }
    }

    public static void drawMonsters(GameplayActor actor, Batch batch, Animation monsterAnimation, float speedo, float yPos, float width, float height)
    {
        for(int i=0; i<actor.getMonsterAnimationArray().size(); i++) {
            actor.getMonstersPositions().set(i,actor.getMonstersPositions().get(i)-Gdx.graphics.getDeltaTime()*speedo);
            if(actor.getMonstersPositions().get(i)<=actor.getPlayerPosition() && !actor.getIsMonsterDead().get(i)) {
                actor.die();
                //System.out.println("edu is gay");
                batch.draw(actor.getDeathMessage(), 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
            if(actor.getMonsterAnimationArray().get(i)>0f)
                actor.getMonsterAnimationArray().set(i, actor.getMonsterAnimationArray().get(i)+Gdx.graphics.getDeltaTime());

            batch.draw((TextureRegion) monsterAnimation.getKeyFrame(actor.getMonsterAnimationArray().get(i)),
                    actor.getMonstersPositions().get(i), Gdx.graphics.getHeight() / yPos, Gdx.graphics.getWidth() / width, Gdx.graphics.getHeight() / height);

            if(monsterAnimation.isAnimationFinished(actor.getMonsterAnimationArray().get(i))) {
                actor.getMonstersPositions().remove(i);
                actor.getIsMonsterDead().remove(i);
                actor.getMonsterAnimationArray().remove(i--); // index hack para deletar itens enquanto itera
            }
        }
    }

    //public static void
}
