package com.example.androidstudio2dgame;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {

    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private int outerCircleCenterPosX;
    private int outerCircleCenterPosY;
    private int innerCircleCenterPosX;
    private int innerCircleCenterPosY;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    @SuppressLint("Range")
    public Joystick(int centerPosX, int centerPosY, int outerCircleRadius, int innerCircleRadius){

        //posizione dei due cerchi concentrici che fanno il joystick
        outerCircleCenterPosX=centerPosX;
        outerCircleCenterPosY=centerPosY;
        innerCircleCenterPosX=centerPosX;
        innerCircleCenterPosY=centerPosY;

        //raggio dei cerchi
        this.outerCircleRadius=outerCircleRadius;
        this.innerCircleRadius=innerCircleRadius;

        //disegnare i cerchi
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY); //forse è sbagliato
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.RED); //forse è sbagliato (no)
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);


    }

    //inserisco i due cerchi dentro il canvas
    public void draw(Canvas canvas) {
        canvas.drawCircle(outerCircleCenterPosX,outerCircleCenterPosY,outerCircleRadius,outerCirclePaint);
        canvas.drawCircle(innerCircleCenterPosX,innerCircleCenterPosY,innerCircleRadius,innerCirclePaint);
    }

    public void update() {
        innerCircleCenterPosX= (int) (outerCircleCenterPosX + actuatorX*outerCircleRadius);
        innerCircleCenterPosY= (int) (outerCircleCenterPosY + actuatorY*outerCircleRadius);
    }
    //quando viene premuto si fa pitagora e si ritorna vero se il punto dove ho premuto è dentro il raggio esterno, falso altrimenti
    public boolean isPressed(double touchPosX, double touchPosY) {
        joystickCenterToTouchDistance = Math.sqrt(
                Math.pow(outerCircleCenterPosX- touchPosX, 2) +
                Math.pow(outerCircleCenterPosY - touchPosY, 2)
        );
        return joystickCenterToTouchDistance < outerCircleRadius;
    }

    //i vari metodi implementati

    public void setIsPressed(boolean isPressed) {
        this.isPressed=isPressed;
    }

    public boolean getIsPressed() {
    return isPressed;
    }

    public void setActuator(double touchPosX, double touchPosY) {
        double deltaX= touchPosX - outerCircleCenterPosX;
        double deltaY= touchPosY - outerCircleCenterPosY;
        double deltaDistance=Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));

        if (deltaDistance < outerCircleRadius){
            actuatorX= deltaX/outerCircleRadius;
            actuatorY=deltaY/outerCircleRadius;
        }    else {
            actuatorX= deltaX/deltaDistance;
            actuatorY=deltaY/deltaDistance;

        }


    }

    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }
    public double getActuatorY() {
        return actuatorY;
    }
}
