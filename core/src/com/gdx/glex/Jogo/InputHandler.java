package com.gdx.glex.Jogo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.gdx.glex.Menu.MenuPage;

// Classe que lida com os Inputs, herda de InputProcessor e tem funcoes bem obveas
public class InputHandler implements InputProcessor {

    private int state=0;
    GameplayActor game;

    InputHandler(GameplayActor game)
    {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Buttons.LEFT:
                // todo porrada
                break;
            case Input.Keys.SPACE:
                // se for par corre caso contrario desacelera
                game.setRunning(state++%2==0);
                break;
        }

        return false;
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
