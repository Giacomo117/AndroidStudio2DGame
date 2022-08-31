package com.example.androidstudio2dgame;

import android.graphics.Canvas;

//questa classe è la classe madre di nemici, player, ecc, e ha dentro tutte le cose in comune

public abstract class GameObject {
   public double positionX;
    public double positionY;
    public double velocityX;
    public double velocityY;

    public GameObject(double positionX, double positionY)
    {
        this.positionX=positionX;
        this.positionY=positionY;
    }

    public abstract void draw(Canvas canvas);   // draw e update sono diversi e quindi sono astratti, perchè ogni classe specifica li implementerà
    public abstract void update();
}
