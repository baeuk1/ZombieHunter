package com.example.baeuk.zombiehunter;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by baeuk on 2015-12-01.
 */
public class LV3Activity extends Activity {
    GraphicsView zombieGame;
    private final int level = 3;
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
