package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by baeuk on 2015-12-01.
 */
public class Katpa extends Zombie {

    private void constructor(Context context){
        downSpeed = 5.5;
        LANE_NUM = 3;
        LANE_GAP = 160;
        zombie = ContextCompat.getDrawable(context,R.drawable.katpaoriginal);
        life = 1;
    }
    public Katpa(Context context){
        super(context);
        constructor(context);
    }
    public Katpa(Context context, int level){
        super(context);
        constructor(context);
        downSpeed = 4;
    }
}
