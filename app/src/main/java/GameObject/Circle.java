package GameObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

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

    public static boolean isColliding(Circle obj1, Circle obj2) {
        //devo capire se due circonferenze si toccano
        double distance= getDistanceBetweenObj(obj1, obj2);
        double distanceToCollision= obj1.getRadius() + obj2.getRadius();
        if (distance < distanceToCollision){
            return true;
        } else {
            return false;
        }
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float)positionY,(float)radius,paint); //HO DECISO CHE IL PLAYER E' UNA PALLINA VIOLA
    }
}
