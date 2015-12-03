package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by baeuk on 2015-12-01.
 */
public class Issha extends Zombie {
    private void constructor(Context context){
        downSpeed = 3;
        LANE_NUM = 3;
        LANE_GAP = 160;
        zombie = ContextCompat.getDrawable(context, R.drawable.issha);
        life = 2;
    }
    public Issha(Context context){
        super(context);
        constructor(context);
    }
    public Issha(Context context, int level){
        super(context);
        constructor(context);
        downSpeed = 2.5;
    }
}
