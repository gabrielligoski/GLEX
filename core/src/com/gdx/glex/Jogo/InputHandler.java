package com.gdx.glex.Jogo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.gdx.glex.Menu.MenuPage;

import java.util.Locale;

// Classe que lida com os Inputs, herda de InputProcessor e tem funcoes bem obveas
public class InputHandler implements InputProcessor {

    private int state=0;
    private GameplayActor game;
    private String name="";

    InputHandler(GameplayActor game)
    {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(game.isDead())
            name+=Character.isLetter(keycode+36)?Input.Keys.toString(keycode).toLowerCase():"";

        if(game.isEnterPressed() && Input.Keys.ENTER==keycode)
            game.endGame();

        switch (keycode)
        {
            case Input.Keys.CONTROL_LEFT:
                // todo porrada
                game.setAttacking(true);
                break;
            case Input.Keys.SPACE:
                // se for par corre caso contrario desacelera
                game.setRunning(state++%2==0);
                break;
            case Input.Keys.ENTER:
                if (game.isDead())
                    game.setEnterPressed();
                break;
        }

        return false;
    }

    public String getName() {
        return name.substring(5);
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
