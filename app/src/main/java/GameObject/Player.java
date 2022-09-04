package GameObject;

import android.content.Context;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.androidstudio2dgame.GameLoop;
import com.example.androidstudio2dgame.Joystick;
import com.example.androidstudio2dgame.R;


//il player è il personaggio principale, ed è l'unico che viene mosso attraverso un joystick.
//la classe player è estensione di circle, che è estensione di GameObject
public class Player extends Circle{

    private final Joystick joystick;
    private double radius;
    private Paint paint;
    public static final double SPEED_PIXEL_PER_SEC = 1400.0 ;
    public static final double MAX_SPEED= SPEED_PIXEL_PER_SEC / GameLoop.MAX_UPS;


    //COSTRUTTORE
    public Player(Context context,Joystick joystick, double positionX, double positionY, double radius){
        super(context, ContextCompat.getColor(context, R.color.purple_200), positionX, positionY,radius); //in questo modo recuperiamo i dati del costruttore in GameObject
        this.joystick= joystick;
    }



    public void update() {
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
