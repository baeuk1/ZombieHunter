package com.example.baeuk.zombiehunter;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by baeuk on 2015-12-03.
 */
public class LV4Activity extends Activity {
    GraphicsView zombieGame;
    private final int level = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zombieGame = new GraphicsView(this,level);
        setContentView(zombieGame);
    }
    @Override
    protected void onPause(){
        super.onPause();
        zombieGame.createSoundPool();
    }
}
