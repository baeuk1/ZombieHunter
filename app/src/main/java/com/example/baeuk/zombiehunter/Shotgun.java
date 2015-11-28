package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by baeuk on 2015-11-04.
 */
public class Shotgun {
    private Paint gunPaint;
    private Drawable shotgun;
    private final int GUNTOP = 790;
    private final int GUNBOTTOM = GUNTOP + 100;
    private final int GUNLEFTSTART = 90;
    private final int GUNLEFTEND = GUNLEFTSTART + 100;
    private final int GUNMIDDLESTART = GUNLEFTEND + 120;
    private final int GUNMIDDLEEND = GUNMIDDLESTART + 100;
    private final int GUNRIGHTSTART = GUNMIDDLEEND + 120;
    private final int GUNRIGHTEND = GUNRIGHTSTART + 100;
    private final int GUN_STATE_LEFT = 1;
    private final int GUN_STATE_CENTER = 2;
    private final int GUN_STATE_RIGHT = 3;

    private int gunState;

    public Shotgun(Context context){
        shotgun = ContextCompat.getDrawable(context,R.drawable.gunoriginal);
        shotgun.setBounds(GUNMIDDLESTART,GUNTOP,GUNMIDDLEEND,GUNBOTTOM);
        gunState = GUN_STATE_CENTER;
    }
    public void draw(Canvas canvas){
        shotgun.draw(canvas);
    }
    public void execute(int command){
        switch(command){
            case 1: {
                if(gunState == 1);
                else if(gunState == 2){
                    shotgun.setBounds(GUNLEFTSTART,GUNTOP,GUNLEFTEND,GUNBOTTOM);
                    gunState = 1;
                }
                else if(gunState == 3){
                    shotgun.setBounds(GUNMIDDLESTART,GUNTOP,GUNMIDDLEEND,GUNBOTTOM);
                    gunState = 2;
                }
            } break;
            case 2: {

            } break;
            case 3: {
                if(gunState == 1){
                    shotgun.setBounds(GUNMIDDLESTART,GUNTOP,GUNMIDDLEEND,GUNBOTTOM);
                    gunState = 2;
                }
                else if(gunState == 2){
                    shotgun.setBounds(GUNRIGHTSTART,GUNTOP,GUNRIGHTEND,GUNBOTTOM);
                    gunState = 3;
                }
                else if(gunState == 3);
            } break;
            default: break;
        }
    }
}
