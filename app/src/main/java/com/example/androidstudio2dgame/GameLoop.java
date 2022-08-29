package com.example.androidstudio2dgame;

import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {
    private Game game;
    private boolean isRunning=false;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;
    private static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
//suca tommi200 fack iu giec
    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game=game;
        this.surfaceHolder=surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
        }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        isRunning=true;
        start();
    }

    @Override
    public void run() {
        super.run();
        Canvas canvas; //è un'area nera rettangolare dove ci si può disegnare

        int updateCount=0;
        int frameCount=0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        startTime=System.currentTimeMillis(); //imposto il tempo di partenza quando faccio partire il loop

        //Full Game Loop while the app is running
        while(isRunning){

            //fasi:

            //update e render game

            try{
                canvas=surfaceHolder.lockCanvas();
                game.update();
                game.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            updateCount++;
            frameCount++;

            //pause Game Loop to not exceed target UPS
            elapsedTime=System.currentTimeMillis() - startTime; //tempo trascorso
            sleepTime= (long) (updateCount*UPS_PERIOD - elapsedTime);
            if (sleepTime>0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Skip frames to keep up with target UPS

            while(sleepTime < 0 && updateCount < MAX_UPS-1){
                game.update();
                updateCount++;
                elapsedTime=System.currentTimeMillis() - startTime; //tempo trascorso
                sleepTime= (long) (updateCount*UPS_PERIOD - elapsedTime);
            }


            //calculate average FPS and UPS

            elapsedTime=System.currentTimeMillis() - startTime; //tempo trascorso
            if (elapsedTime>=1000){
                averageUPS= updateCount / (1E-3*elapsedTime);
                averageFPS= frameCount / (1E-3*elapsedTime);
                updateCount=0;
                frameCount=0;
                startTime=System.currentTimeMillis(); //riazzero il tempo di partenza
            }
        }
    }
}
