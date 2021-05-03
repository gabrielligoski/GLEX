package com.gdx.glex.Jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.glex.Assets;
import com.gdx.glex.AuxiliarFunctions.AnimationFunctions;
import com.gdx.glex.AuxiliarFunctions.GameplayFunctions;

import java.util.ArrayList;

// Classe Actor, que eh basicamente como a tela deve ser mostrada no momento que
public class GameplayActor extends Actor
{
    private float elapsedTime, playerPosition, attackElapsedTime;
    private int pontuacao;
    private BitmapFont font;
    private Texture deathMessage;

    private boolean isRunning;
    private boolean isAttacking;
    private boolean isDead;

    private ArrayList<Boolean> isMonsterDead = new ArrayList<>();

    private ArrayList<Float> monstersPositions = new ArrayList<>();
    private ArrayList<Float> monsterAnimationArray = new ArrayList<>();
    private Animation attackAnimation, runAnimation, monsterAnimation, ghostAnimation;


    GameplayActor(Assets assetsManager)
    {
        deathMessage = assetsManager.manager.get("Imagens/Gameplay/deathMessage.png");
        font = assetsManager.manager.get("Fonts/OldFont.fnt");

        attackAnimation = AnimationFunctions.png2Animation("Animations/playerAttack.png", assetsManager, 12, 1, 80, 80, 0, 12, 12f );

        runAnimation = AnimationFunctions.png2Animation("Animations/playerRun.png", assetsManager, 6, 1, 80, 80, 0, 6, 6f );

        monsterAnimation = AnimationFunctions.png2Animation("Animations/monstro.png", assetsManager, 7,1, 275, 275, 0, 7, 7f );

        ghostAnimation = AnimationFunctions.png2Animation("Animations/ghost.png", assetsManager, 7, 1, 64,64, 0, 7, 10f );

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

    // mata o protagonista
    public void die()
    {
        isDead=true;
    }

    public void killMonster()
    {
        for(int i=0; i<monsterAnimationArray.size(); i++)
        {
            if(monstersPositions.get(i)<=playerPosition+Gdx.graphics.getWidth()/5f)
            {
                isMonsterDead.set(i, true);
                monsterAnimationArray.set(i, .1f);
                pontuacao+=100;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        font.draw(batch, "Score: " + pontuacao, Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/6f, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6f);

        GameplayFunctions.spawnMonster(monstersPositions, monsterAnimationArray, isMonsterDead);

        GameplayFunctions.drawMonsters(this, batch, monsterAnimation);

        // mata o player se ele for engolido pelo WoF
        if(playerPosition<=Gdx.graphics.getWidth()/20f) {
            die();
            batch.draw(deathMessage, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        // se nao estiver morto continua printando as coisas
        if(!isDead) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            // se o jogador estiver estiver correndo ele vai para frente caso contrario vai para tras
            playerPosition += isRunning&&playerPosition<Gdx.graphics.getWidth()-500f ? Gdx.graphics.getDeltaTime() * 300f : -Gdx.graphics.getDeltaTime() * 200f;
        }
        // desenha personagem correndo se nao estiver atacando caso contrario desenha ele atacando
        if(!isAttacking || isDead)
            batch.draw((TextureRegion) runAnimation.getKeyFrame(elapsedTime, true),
                    playerPosition,Gdx.graphics.getHeight()/20f, Gdx.graphics.getWidth()/3f, Gdx.graphics.getHeight()/3f);
        else {
            // confere se a animacao terminou se nao continua nela
            if(!attackAnimation.isAnimationFinished(attackElapsedTime)) {
                killMonster();
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


    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public float getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(float playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Texture getDeathMessage() {
        return deathMessage;
    }

    public void setDeathMessage(Texture deathMessage) {
        this.deathMessage = deathMessage;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public ArrayList<Boolean> getIsMonsterDead() {
        return isMonsterDead;
    }

    public void setIsMonsterDead(ArrayList<Boolean> isMonsterDead) {
        this.isMonsterDead = isMonsterDead;
    }

    public ArrayList<Float> getMonstersPositions() {
        return monstersPositions;
    }

    public void setMonstersPositions(ArrayList<Float> monstersPositions) {
        this.monstersPositions = monstersPositions;
    }

    public ArrayList<Float> getMonsterAnimationArray() {
        return monsterAnimationArray;
    }

    public void setMonsterAnimationArray(ArrayList<Float> monsterAnimationArray) {
        this.monsterAnimationArray = monsterAnimationArray;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}