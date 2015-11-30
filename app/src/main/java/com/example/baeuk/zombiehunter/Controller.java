package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;

/**
 * Created by baeuk on 2015-11-04.
 */
public class Controller {
    private Paint paint_LR;
    private Paint paint_shot;
    private Rect shotbutton;
    private Drawable left;
    private Drawable right;
    private final int BTTOP = 900;
    private final int BTBOTTOM = BTTOP + 100;
    private final int BTLEFTSTART = 90;
    private final int BTLEFTEND = BTLEFTSTART + 100;
    private final int BTMIDDLESTART = BTLEFTEND + 50;
    private final int BTMIDDLEEND = BTMIDDLESTART + 240;
    private final int BTRIGHTSTART = BTMIDDLEEND + 50;
    private final int BTRIGHTEND = BTRIGHTSTART + 100;
    private final int BT_MOVE_LEFT = 1;
    private final int BT_SHOOT = 2;
    private final int BT_MOVE_RIGHT = 3;

    public Controller(Context context){
        paint_LR = new Paint();
        paint_shot = new Paint();
        shotbutton = new Rect();
        left = ContextCompat.getDrawable(context,R.drawable.left);
        right = ContextCompat.getDrawable(context,R.drawable.right);

        paint_LR.setColor(Color.RED);
        paint_LR.setTextSize(70);
        paint_shot.setColor(Color.BLUE);

        left.setBounds(BTLEFTSTART, BTTOP, BTLEFTEND, BTBOTTOM);
        shotbutton.set(BTMIDDLESTART, BTTOP, BTMIDDLEEND, BTBOTTOM);
        right.setBounds(BTRIGHTSTART,BTTOP,BTRIGHTEND,BTBOTTOM);
    }

    public void draw(Canvas canvas){
        left.draw(canvas);
        right.draw(canvas);
        canvas.drawRect(shotbutton, paint_shot);
        canvas.drawText("SHOT", 290, 970, paint_LR);
    }
    public int onTouchEvent(MotionEvent event){
        float currentX = event.getX();
        float currentY = event.getY();

        if(currentX>=BTLEFTSTART && currentX<=BTLEFTEND && currentY>=BTTOP && currentY<=BTBOTTOM){
            return BT_MOVE_LEFT;
        }
        else if(currentX>=BTMIDDLESTART && currentX<=BTMIDDLEEND && currentY>=BTTOP && currentY<=BTBOTTOM){
            return BT_SHOOT;
        }
        else if(currentX>=BTRIGHTSTART && currentX<=BTRIGHTEND && currentY>=BTTOP && currentY<=BTBOTTOM){
            return BT_MOVE_RIGHT;
        }
        else return 0;
    }
}
