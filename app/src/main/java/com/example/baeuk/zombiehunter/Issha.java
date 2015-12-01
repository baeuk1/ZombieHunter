package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by baeuk on 2015-12-01.
 */
public class Issha extends Zombie {
    public Issha(Context context){
        super(context);
        downSpeed = 3;
        LANE_NUM = 3;
        LANE_GAP = 160;
        zombie = ContextCompat.getDrawable(context, R.drawable.issha);
    }
}
