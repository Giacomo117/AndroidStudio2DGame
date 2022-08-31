package com.example.androidstudio2dgame;

import android.content.Context;

import androidx.core.content.ContextCompat;

public class Enemy extends Circle{


    public Enemy(Context context, int color, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context,R.color.magenta), positionX, positionY,radius);
    }

    @Override
    public void update() {
        //dobbiamo aggiornare la velocità del nemico in modo che sia sempre direzionata verso il personaggio

        //calcolo il vettore tra il player e il nemico (x,y)

        //calcolo la distanza

        //calcolo la direzione

        //setto la velocità nella direzione trovata

        //aggiorno la posizione del nemico



    }
}
