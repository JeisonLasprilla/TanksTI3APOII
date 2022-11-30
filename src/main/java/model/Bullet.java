package model;

import com.example.ti3.HelloApplication;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet {

    private Canvas canvas;
    private GraphicsContext gc;
    private Image bullet;
    public Vector pos;
    private Vector direction;

    public Bullet(Canvas canvas, String bulletPath, double x,double y, Vector direction) {

        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ HelloApplication.class.getResource(bulletPath).getPath();
        this.bullet = new Image(uri);
        this.pos = new Vector(x*1, y*1);
        this.direction = Vector.instanceOf(direction);
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate( 180+ direction.getAngle());
        gc.drawImage(bullet, -7,-8, 40,40);
        gc.restore();
    }

    public void colision(){
        gc.drawImage(null, 0,0,0,0);
    }

    public void move(){
        pos.x += direction.x;
        pos.y += direction.y;
    }
}
