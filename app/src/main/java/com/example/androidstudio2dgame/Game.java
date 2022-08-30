package com.example.androidstudio2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import androidx.core.content.ContextCompat;
/**
 * Questo Game controlla tutti gli oggetti nel gioco ed è responsabile degli aggiornamenti dei dati e delle immagini nello schermo
 * */


class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final GameLoop gameLoop;
    private final Context context;
    private final Joystick joystick;

    public Game(Context context) {
        super(context);
        this.context=context;
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);  //così possiamo usare il touch input


        gameLoop=new GameLoop(this,surfaceHolder); //creo un loop del gioco sul surfaceHolder che ho creato

        //inizializzo il joystick
        joystick=new Joystick(275,750,70,40);

        //inizializzo il player
        player=new Player(getContext(),2*500,500,30);

        setFocusable(true); //sarebbe per permettere di dare il focus ad un componente, non so se serve, (forse si)



    }

    @Override
    public void surfaceCreated( SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    //per gestire i vari tocchi touchsullo schermo
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //per il joystick
                if(joystick.isPressed((double)event.getX(),(double)event.getY())){
                    joystick.setIsPressed(true);

                }
                return true;
                //per il player
                //player.setPosition((double)event.getX(),(double)event.getY());
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double)event.getX(),(double)event.getY());

                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;


        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed( SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUpdatePerSec(canvas);
        drawFramePerSec(canvas);
        joystick.draw(canvas);
        player.draw(canvas); //per disegnare il player

    }

    public void drawUpdatePerSec(Canvas canvas){
        String avrUPS= Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(45);
        canvas.drawText("UPS: " + avrUPS, 20, 90, paint);
    }

    public void drawFramePerSec(Canvas canvas){
        String avrFPS= Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(45);
        canvas.drawText("FPS: " + avrFPS, 20, 50, paint);
    }

    public void update() {
        joystick.update(); // joystick
        player.update(joystick); // per aggiornare il personaggio

    }
}