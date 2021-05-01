package com.gdx.glex.Menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

// Classe que lida com os Inputs, herda de InputProcessor e tem funcoes bem obveas
public class InputHandler implements InputProcessor {

    MenuPage screen;

    // recebe a tela onde o Input esta sendo gerado
    public InputHandler(MenuPage screen)
    {
        this.screen = screen;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Keys.S:
                screen.changeSelectedButtonId(1);
                break;
            case Input.Keys.W:
                screen.changeSelectedButtonId(-1);
                break;
            case Input.Keys.ENTER:
                screen.callSelectedButton();
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
