package com.gdx.glex.Menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.gdx.glex.Menu.MenuPrincipal.Menu;

// Classe que lida com os Inputs, herda de InputProcessor e tem funcoes bem obveas
public class InputHandler implements InputProcessor {

    MenuPage screen;
    
    //Analiza qual pagina do menu eh para determinar os botoes de avanco do selectedbuttonid
    int up, down;

    // recebe a tela onde o Input esta sendo gerado
    public InputHandler(MenuPage screen, String name)
    {
        this.screen = screen;
        if (name.equals("rankings")) {
            up = Input.Keys.D;
            down = Input.Keys.A;
        } 
        if(name.equals("menu")){
            up = Input.Keys.W;
            down = Input.Keys.A;
        }
    }


    @Override
    public boolean keyDown(int keycode) {
        if(up == Input.Keys.W){
            switch (keycode){
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
        }
        if (up == Input.Keys.D){
            switch (keycode){
                case Input.Keys.A:
                    screen.changeSelectedButtonId(1);
                    break;
                case Input.Keys.D:
                    screen.changeSelectedButtonId(-1);
                    break;
                case Input.Keys.ENTER:
                    screen.callSelectedButton();
                    break;
            }
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
