package com.example.baeuk.zombiehunter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by baeuk on 2015-11-04.
 */
public class GraphicsView extends View {
    private Context context;
    private Shotgun shotgun;
    private Controller controller;
    private Zombie zombie[];
    private Katpa katpa[];
    private Paint white;
    private Paint deadline;
    private Paint laneline;
    private SoundPool.Builder gunSoundBuilder;
    private SoundPool gunSound;
    private AudioAttributes.Builder attributesBuilder;
    private AudioAttributes attributes;
    private ZombieMakeThread zombieMakeThread;
    private int shotSound;
    private float linewidth;
    private float lineheight;
    private String numofKills;
    private int level;
    private int kills;
    private int currentNumOfZombies = 0;
    private int maxZombies;
    private boolean zombieCreate = true;
    private boolean gameOverFlag = false;
    private boolean gameClearFlag = false;
    private final int ZENSPEED = 200; //lower is faster.
    private final int ZENTERM = 400;
    private final int MOVE_LEFT = 1;
    private final int SHOOT = 2;
    private final int MOVE_RIGHT = 3;
    private final int DEADLINE = 730;
    private long previousTime = 0;
    private int[] zombieLane;
    private int[] numberofZombiesbyLevel = { 30, 50, 60 };

    class ZombieMakeThread extends Thread{
        Context context;
        int level;
        public ZombieMakeThread(Context context, int level){
            this.context = context;
            this.level = level;
        }
        @Override
        public void run(){
            while(currentNumOfZombies < maxZombies){
                if(zombieCreate == true){
                    switch (level){
                        case 1 : zombie[currentNumOfZombies] = new Zombie(context); break;
                        case 2 : zombie[currentNumOfZombies] = new Katpa(context); break;
                        case 3 : zombie[currentNumOfZombies] = new Issha(context); break;
                        default : break;
                    }
                    zombieLane[currentNumOfZombies] = zombie[currentNumOfZombies].getPosition();
                    currentNumOfZombies++;
                    try {
                        Thread.sleep((int)(Math.random() * ZENTERM) + ZENSPEED);
                    } catch (InterruptedException e) {
                        continue;
                    }
                }
            }
        }
    }

    public GraphicsView(Context context, int level){
        super(context);
        this.context=context;
        createSoundPool();
        maxZombies = numberofZombiesbyLevel[level-1];
        zombieLane = new int[maxZombies];
        zombie = new Zombie[maxZombies];
        katpa = new Katpa[maxZombies];
        zombieMakeThread = new ZombieMakeThread(context, level);
        controller = new Controller(context);
        shotgun = new Shotgun(context);
        white = new Paint();
        deadline = new Paint();
        laneline = new Paint();
        this.level = level-1;
        white.setColor(Color.WHITE);
        deadline.setColor(Color.RED);
        laneline.setColor(Color.DKGRAY);
        white.setTextSize(40);
        numofKills = "Kills : 0";
        kills = 0;

        for(int i=0; i<maxZombies; i++) zombieLane[i]=0;
        zombieMakeThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas){
        controller.draw(canvas);
        shotgun.draw(canvas);
        for(int i=0; i<currentNumOfZombies; i++){
            if(zombie[i]!=null) zombie[i].draw(canvas); // To draw every existing zombies
        }
        if(checkGameOver()==true) gameOver();
        if(checkGameClear()==true) gameClear();
        drawLine(canvas);
        canvas.drawText(numofKills,260,1040,white);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        linewidth = w / 9f;
        lineheight = h / 8f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int shotPosition;
         Log.v("time",Long.toString(event.getEventTime()-previousTime)); // for Debug
        if(gameOverFlag == false && event.getEventTime()-previousTime > 120){
            switch(controller.onTouchEvent(event)){
                case 1:{
                    previousTime = event.getEventTime();
                    shotgun.execute(MOVE_LEFT);
                } break;
                case 2: {
                    previousTime = event.getEventTime();
                    gunSound.play(shotSound,0.99f,0.99f,0,0,1.5f);
                    shotPosition = shotgun.execute(SHOOT);
                    for(int i=0; i<currentNumOfZombies; i++){
                        if(zombieLane[i]==shotPosition){
                            if(zombie[i].getLife(level) == 1) {
                                zombie[i]=null;
                                zombieLane[i]=0;
                                numofKills = "Kills : " + ++kills;
                            }
                            else zombie[i].decreaseLife(level);
                            break;
                        }
                    }
                } break;
                case 3:{
                    previousTime = event.getEventTime();
                    shotgun.execute(MOVE_RIGHT);
                } break;
                default: break;
            }
        }
        return true;
    }

    public void createSoundPool(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            attributesBuilder = new AudioAttributes.Builder();
            attributesBuilder.setUsage(AudioAttributes.USAGE_GAME);
            attributesBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
            attributes = attributesBuilder.build();

            gunSoundBuilder = new SoundPool.Builder();
            gunSoundBuilder.setAudioAttributes(attributes);
            gunSound = gunSoundBuilder.build();
        }
        else{
            gunSound  = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }
        shotSound = gunSound.load(context,R.raw.gunshot,1);
    }

    private boolean checkGameClear(){
        if(currentNumOfZombies == maxZombies) {
            for (int i = 0; i < maxZombies; i++) {
                if (zombie[i] != null) return false;
            }
            return true;
        }
        return false;
    }

    private void gameClear(){
        if(gameClearFlag == false) Toast.makeText(context,"GAME CLEAR!!",Toast.LENGTH_SHORT).show();
        gameClearFlag = true;
    }

    private boolean checkGameOver(){
        for(int i=0; i<currentNumOfZombies; i++) {
            if (zombie[i] != null && zombie[i].getBottomCoordinate() >= DEADLINE) {
                zombieCreate = false;
                return true;
            }
        }
        return false;
    }

    private void gameOver(){
        // When zombie couldn't be created because someone passes the deadline, Everyone stops.
        if(zombieCreate == false) {
            for (int i = 0; i < currentNumOfZombies; i++)
                if (zombie[i] != null) zombie[i].downSignal = false;
            if(gameOverFlag == false) Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT).show();
            gameOverFlag = true;
        }
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
