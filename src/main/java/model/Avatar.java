package model;

import com.example.ti3.HelloApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Avatar {

    private int NBullet = 6;

    private int NLifes = 5;
    private Canvas canvas;
    private GraphicsContext gc;

    private Life lifes [] = new Life[10];

    private Bullet bullets [] = new Bullet[12];

    private Image tank;

    private Vector pos;
    private Vector direction;

    public Avatar(Canvas canvas, String colorTank , Vector pos, Vector direction, Vector L0pos, Vector L1pos, Vector L2pos, Vector L3pos, Vector L4pos, Vector B0pos, Vector B1pos, Vector B2pos, Vector B3pos, Vector B4pos, Vector B5pos){
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String url = "file:"+HelloApplication.class.getResource(colorTank).getPath();
        tank = new Image(url);
        this.pos = pos;
        this.direction = direction;

        String lifeURL = "file:"+HelloApplication.class.getResource("life.png").getPath();
        lifes[0] = new Life(new Image(lifeURL), L0pos);
        lifes[1] = new Life(new Image(lifeURL), L1pos);
        lifes[2] = new Life(new Image(lifeURL), L2pos);
        lifes[3] = new Life(new Image(lifeURL), L3pos);
        lifes[4] = new Life(new Image(lifeURL), L4pos);

        String lifelessURL = "file:"+HelloApplication.class.getResource("lifeless.png").getPath();
        lifes[5] = new Life(new Image(lifelessURL), new Vector(0,0));
        lifes[6] = new Life(new Image(lifelessURL), new Vector(0,0));
        lifes[7] = new Life(new Image(lifelessURL), new Vector(0,0));
        lifes[8] = new Life(new Image(lifelessURL), new Vector(0,0));
        lifes[9] = new Life(new Image(lifelessURL), new Vector(0,0));

        String bulletURL = "file:"+HelloApplication.class.getResource("bullet.png").getPath();
        bullets[0] = new Bullet(new Image(bulletURL), B0pos);
        bullets[1] = new Bullet(new Image(bulletURL), B1pos);
        bullets[2] = new Bullet(new Image(bulletURL), B2pos);
        bullets[3] = new Bullet(new Image(bulletURL), B3pos);
        bullets[4] = new Bullet(new Image(bulletURL), B4pos);
        bullets[5] = new Bullet(new Image(bulletURL), B5pos);
    }

    public int getNBullet() {
        return NBullet;
    }

    public void setNBullet(int NBullet) {
        this.NBullet = NBullet;
    }

    public int getNLifes() {
        return NLifes;
    }

    public void setNLifes(int NLifes) {
        this.NLifes = NLifes;
    }

    public void drawLife(){

        //life
        for (int i = 0; i < NLifes-1; i++) {
            gc.drawImage(lifes[i].getImage(), 0,0, 50,50);
        }

        //lifeless
        for (int i = NLifes; i < 10; i++) {
            gc.drawImage(lifes[i].getImage(), 0,0, 50,50);
        }
    }

    public void drawBullet(){

    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate(90+direction.getAngle());
        gc.drawImage(tank, -25,-25, 50,50);
        drawLife();
        gc.restore();
    }
    public void setPosition(double x, double y) {
        pos.x = (int) x - 25;
        pos.y = (int) y - 25;
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

}
