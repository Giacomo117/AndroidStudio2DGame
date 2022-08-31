package com.example.androidstudio2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public abstract class Circle extends GameObject {
    private double radius;
    private Paint paint;

    public Circle(Context context, int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);
        this.radius=radius;

        //set color of circles
        paint=new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float)positionY,(float)radius,paint); //HO DECISO CHE IL PLAYER E' UNA PALLINA VIOLA
    }
}
