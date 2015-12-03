package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by baeuk on 2015-12-03.
 */
public class Person extends Zombie{
    public Person(Context context){
        super(context);
        downSpeed = 8;
        LANE_NUM = 3;
        LANE_GAP = 160;
        zombie = ContextCompat.getDrawable(context, R.drawable.person);
        life = 1;
    }
    @Override
    public boolean isPerson(){ return true; }
}
