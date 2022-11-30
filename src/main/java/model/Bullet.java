package model;

import com.example.ti3.HelloApplication;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet {

    private Image status;

    private Vector statusPos;

    private Canvas canvas;
    private GraphicsContext gc;
    private Image bullet;
    private Vector pos;
    private Vector direction;

    public Bullet(Canvas canvas, String bulletPath, double x,double y, Vector direction) {
        status = null;
                statusPos = new Vector(0,0);
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ HelloApplication.class.getResource(bulletPath).getPath();
        this.bullet = new Image(uri);
        this.pos = new Vector(x*1, y*1);
        this.direction = Vector.instanceOf(direction);
    }
   /* public Bullet (Bullet newBullet) {
        this( newBullet.canvas, "bulletStatus.png", newBullet.pos.x,newBullet.pos.y, new Vector(newBullet.direction));
    }

    public static Bullet newInstance(Bullet newBullet) {
        return new Bullet(newBullet.canvas, "bulletStatus.png",newBullet.pos.x,newBullet.pos.y,  new Vector(newBullet.direction));
    }*/

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.drawImage(bullet, -25,-25, 50,50);
        gc.restore();
    }

    public void move(){
        pos.x += direction.x;
        pos.y += direction.y;
    }

    public void setPos(double x, double y) {
        this.pos = new Vector(x , y );
    }

    public void setDirection(double x, double y) {
        this.direction = new Vector(x, y);
    }
}
