package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by baeuk on 2015-11-04.
 */
public class GraphicsView extends View {
    Shotgun shotgun = new Shotgun();
    Controller controller = new Controller();
    Zombie zombie;

    Paint white;
    Paint deadline;
    Paint laneline;
    private float linewidth;
    private float lineheight;
    private String numofKills;

    public GraphicsView(Context context){
        super(context);
        white = new Paint();
        deadline = new Paint();
        laneline = new Paint();
        white.setColor(Color.WHITE);
        deadline.setColor(Color.RED);
        laneline.setColor(Color.DKGRAY);
        white.setTextSize(40);
        numofKills = "Kills : ";
    }

    @Override
    protected void onDraw(Canvas canvas){
        controller.draw(canvas);
        drawLine(canvas);
        canvas.drawText(numofKills,260,1040,white);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        linewidth = w / 9f;
        lineheight = h / 8f;
    }

    private void drawLine(Canvas canvas){
        for(int i=0; i<9; i++){
            if(i%3 != 0) continue;
            canvas.drawLine(i*linewidth-1, 0, i*linewidth-1, getHeight(), laneline);
            canvas.drawLine(i*linewidth, 0, i*linewidth, getHeight(), laneline);
            canvas.drawLine(i*linewidth+1, 0, i*linewidth+1, getHeight(), laneline);
            canvas.drawLine(i*linewidth+2, 0, i*linewidth+2, getHeight(), laneline);
        }
        canvas.drawLine(0,6*lineheight-1,getWidth(),6*lineheight-1,deadline);
        canvas.drawLine(0,6*lineheight,getWidth(),6*lineheight,deadline);
        canvas.drawLine(0,6*lineheight+1,getWidth(),6*lineheight+1,deadline);
    }
}
