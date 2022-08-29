package com.example.androidstudio2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import androidx.core.content.ContextCompat;
/**
 * Questo Game controlla tutti gli oggetti nel gioco ed è responsabile degli aggiornamenti dei dati e delle immagini nello schermo
 * */


class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;

    public Game(Context context) {
        super(context);
        this.context=context;
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);  //così possiamo usare il touch input


        gameLoop=new GameLoop(this,surfaceHolder); //creo un loop del gioco sul surfaceHolder che ho creato
        setFocusable(true); //sarebbe per permettere di dare il focus ad un componente, non so se serve, (forse s

    }

    @Override
    public void surfaceCreated( SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
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
    }

    public void drawUpdatePerSec(Canvas canvas){
        String avrUPS= Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(45);
        canvas.drawText("UPS: " + avrUPS, 100, 200, paint);
    }

    public void drawFramePerSec(Canvas canvas){
        String avrFPS= Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(45);
        canvas.drawText("FPS: " + avrFPS, 100, 100, paint);
    }

    public void update() {
    }
}
