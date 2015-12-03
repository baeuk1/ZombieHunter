package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by baeuk on 2015-11-04.
 */
public class Zombie {
    protected Drawable zombie;
    private int randPosition;
    protected int LANE_NUM = 3;
    protected int LANE_GAP = 220;
    private final int INIT_TOP = 0;
    private final int INIT_BOTTOM = 80;
    private int init_left;
    private int init_right;
    private double currentTop;
    private double currentBottom;
    protected int life;
    protected double downSpeed = 4;
    private int randomDownSpeed;
    public boolean downSignal = true;

    private void constructor(Context context){
        randPosition = (int)(Math.random()*LANE_NUM)+1;
        init_left = LANE_GAP*randPosition-120;
        init_right = LANE_GAP*randPosition-40;
        zombie = ContextCompat.getDrawable(context, R.drawable.zombieoriginal);
        zombie.setBounds(init_left,INIT_TOP,init_right,INIT_BOTTOM);
        currentTop=0;
        currentBottom=0;
        life = 1;
    }

    public Zombie(Context context){
        constructor(context);
    }
    public Zombie(Context context, int level){
        constructor(context);
        downSpeed = 3;
    }
    public void draw(Canvas canvas){
        moveDown(zombie);
        zombie.draw(canvas);
    }
    public int getPosition(){ return randPosition; }
    public double getBottomCoordinate(){
        return currentBottom;
    }
    public int getLife(){ return life; }
    public void decreaseLife(){ life--; }
    public boolean isPerson(){ return false; }
    private void moveDown(Drawable zombie){
        if(downSignal == true) {
            randomDownSpeed = (int) Math.random()*3;
            currentTop += downSpeed + randomDownSpeed;
            currentBottom += downSpeed + randomDownSpeed;
            zombie.setBounds(init_left, (int)(INIT_TOP + currentTop), init_right, (int)(INIT_BOTTOM + currentBottom));
        }
    }
}
