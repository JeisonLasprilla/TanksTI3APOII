package model;

import com.example.ti3.HelloApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Avatar {

    private Canvas canvas;
    private GraphicsContext gc;

    private Image tank;

    public Vector pos;
    public Vector direction;

    public Avatar(Canvas canvas, String colorTank , Vector pos, Vector direction){
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+HelloApplication.class.getResource(colorTank).getPath();
        tank = new Image(uri);
        this.pos = pos;
        this.direction = direction;
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate(90+direction.getAngle());
        gc.drawImage(tank, -25,-25, 30,30);
        gc.restore();
    }
    public void setPosition(double x, double y) {
        pos.x = (int) x - 25;
        pos.y = (int) y - 25;
    }

    public Vector getPos() {
        return pos;
    }

    public Vector getDirection() {
        return direction;
    }

    public String setAngle(double angle){
        double amp = direction.getAmplitude();
        direction.x = amp*Math.cos(angle);
        direction.y = amp*Math.sin(angle);
        return " "+Math.toDegrees(angle);
    }

    public void changeAngle(double a){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        angle += a;
        direction.x = amp*Math.cos(Math.toRadians(angle));
        direction.y = amp*Math.sin(Math.toRadians(angle));
    }

    public void moveForward(){
        pos.x += direction.x;
        pos.y += direction.y;
    }
    public void moveBackward(){
        pos.x -= direction.x;
        pos.y -= direction.y;
    }

    public Bullet shoot(){
        //return new Bullet(canvas,"bullet.png", pos.x, pos.y, Vector.instanceOf(direction));
        return null;
    }

}
