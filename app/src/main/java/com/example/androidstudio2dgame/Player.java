package com.example.androidstudio2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private double velocityX;
    private double velocityY;
    private static final double SPEED_PIXEL_PER_SEC = 1400.0 ;
    private static final double MAX_SPEED= SPEED_PIXEL_PER_SEC /GameLoop.MAX_UPS;


    //COSTRUTTORE
    public Player(Context context, double positionX, double positionY, double radius){
        this.positionX=positionX;
        this.positionY=positionY;
        this.radius=radius;


        paint=new Paint();
        int color= ContextCompat.getColor(context,R.color.purple_200); //COLORE DEL PERSONAGGIO
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float)positionY,(float)radius,paint); //HO DECISO CHE IL PLAYER E' UNA PALLINA VIOLA
    }

    public void update(Joystick joystick) {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY= joystick.getActuatorY()*MAX_SPEED;
        positionX+=velocityX;
        positionY+=velocityY;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX=positionX;
        this.positionY=positionY;
    }
}