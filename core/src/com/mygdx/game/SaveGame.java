package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by rkinnucan2330 on 12/6/2017.
 */

public class SaveGame {

    Preferences prefs;

    public SaveGame (Game game){
        create();
    }

    private void create() {
        prefs = new Gdx().app.getPreferences("Player-preferences");
    }
}
