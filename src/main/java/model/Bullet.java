package model;

import com.example.ti3.HelloApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet {
    private Canvas canvas;
    private GraphicsContext gc;
    private Image bullet;
    private Vector pos;
    private Vector direction;

    public Bullet(Canvas canvas, String bulletPath, Vector pos, Vector direction) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ HelloApplication.class.getResource(bulletPath).getPath();
        this.bullet = new Image(uri);
        this.pos = pos;
        this.direction = direction;
    }

    public Bullet (Bullet newBullet) {
        this( newBullet.canvas, "bullet.png", newBullet.pos, newBullet.direction);
    }

    public static Bullet newInstance(Bullet newBullet) {
        return new Bullet(newBullet.canvas, "bullet.png", newBullet.pos, newBullet.direction);
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.drawImage(bullet, -25,-25, 50,50);
        gc.restore();
    }

    public void move(){
        pos.x += 3;
        pos.y += 3;
    }

}
