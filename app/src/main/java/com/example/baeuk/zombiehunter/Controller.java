package com.example.baeuk.zombiehunter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import static com.example.baeuk.zombiehunter.R.drawable.left;

/**
 * Created by baeuk on 2015-11-04.
 */
public class Controller {
    private Paint paint_LR;
    private Paint paint_shot;
    private Rect leftbutton;
    private Rect rightbutton;
    private Rect shotbutton;
    private Drawable left;
    private Drawable rightbtn;
    private final int BTTOP = 900;
    private final int BTBOTTOM = BTTOP + 100;

    public Controller(){
        paint_LR = new Paint();
        paint_shot = new Paint();
        leftbutton = new Rect();
        shotbutton = new Rect();
        rightbutton = new Rect();

        paint_LR.setColor(Color.RED);
        paint_LR.setTextSize(70);
        paint_shot.setColor(Color.BLUE);
        leftbutton.set(90,BTTOP,190,BTBOTTOM);
        shotbutton.set(240,BTTOP,480,BTBOTTOM);
        rightbutton.set(530,BTTOP,630,BTBOTTOM);
    }

    public void draw(Canvas canvas){
        canvas.drawRect(leftbutton,paint_LR);
        canvas.drawRect(shotbutton,paint_shot);
        canvas.drawRect(rightbutton,paint_LR);
        canvas.drawText("SHOT", 290, 970, paint_LR);
    }
}
