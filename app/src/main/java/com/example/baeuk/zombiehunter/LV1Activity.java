package com.example.baeuk.zombiehunter;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by baeuk on 2015-11-30.
 */
public class LV1Activity extends Activity {
    GraphicsView zombieGame;
    private final int level = 1;
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
