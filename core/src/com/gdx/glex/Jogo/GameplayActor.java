package com.gdx.glex.Jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.glex.Assets;

// Classe Actor, que eh basicamente como a tela deve ser mostrada no momento que
public class GameplayActor extends Actor
{
    private float elapsedTime, playerPosition, attackElapsedTime;
    private Assets assetsManger;
    private Texture deathMessage;

    private boolean isRunning;
    private boolean isAttacking;
    private boolean isDead;

    private TextureRegion[] animationFrames;
    private Animation attackAnimation, runAnimation;

    GameplayActor(Assets assetsManager)
    {
        this.assetsManger = assetsManager;

        deathMessage = assetsManager.manager.get("Imagens/Gameplay/deathMessage.png");

        // basicamente pega a imgaem da sprite sheet e divide, depois cria uma animacao com ela
        Texture spriteSheet = assetsManager.manager.get("Animations/playerAttack.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet,80,80);
        animationFrames = new TextureRegion[12];

        for (int i = 0; i < 12; i++){
                animationFrames[i] = tmpFrames[0][i];
        }
        attackAnimation = new Animation(1f/8f,animationFrames);

        spriteSheet = assetsManager.manager.get("Animations/playerRun.png");
        tmpFrames = TextureRegion.split(spriteSheet,80,80);
        animationFrames = new TextureRegion[6];

        for (int i = 0; i < 6; i++){
            animationFrames[i] = tmpFrames[0][i];
        }
        runAnimation = new Animation(1f/6f,animationFrames);

        // seta posicao do player no meio da tela ao começo do jogo
        playerPosition = Gdx.graphics.getWidth()/2f;
    }

    // setter do isRunning
    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        if(playerPosition<=Gdx.graphics.getWidth()/20f) {
            isDead = true;
            batch.draw(deathMessage, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        if(!isDead) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            // se o jogador estiver estiver correndo ele vai para frente caso contrario vai para tras
            playerPosition += isRunning ? Gdx.graphics.getDeltaTime() * 300f : -Gdx.graphics.getDeltaTime() * 200f;
        }
        // desenha personagem correndo se nao estiver atacando caso contrario desenha ele atacando
        if(!isAttacking || isDead)
            batch.draw((TextureRegion) runAnimation.getKeyFrame(elapsedTime, true),
                    playerPosition,Gdx.graphics.getHeight()/20f, Gdx.graphics.getWidth()/3f, Gdx.graphics.getHeight()/3f);
        else {
            // confere se a animacao terminou se nao continua nela
            if(!attackAnimation.isAnimationFinished(attackElapsedTime)) {
                attackElapsedTime+=Gdx.graphics.getDeltaTime();
                batch.draw((TextureRegion) attackAnimation.getKeyFrame(attackElapsedTime, false),
                        playerPosition, Gdx.graphics.getHeight() / 20f, Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 3f);
            }
            // quando a animcao do ataque termina
            else {
                isAttacking = false; // reseta o ataque
                attackElapsedTime=0f; // reseta o tempo da animacao do ataque
            }
        }
    }
}