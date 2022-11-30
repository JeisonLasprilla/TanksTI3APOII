package model;

import com.example.ti3.HelloApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BulletStatus {

    private Canvas canvas;
    private GraphicsContext gc;


    private Image image;
    private Vector pos;

    public BulletStatus(Canvas canvas, Vector pos) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String url = "file:"+ HelloApplication.class.getResource("bulletStatus.png").getPath();
        image = new Image(url);
        this.pos = pos;
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.drawImage(image,-25,-25, 20,20);
        gc.restore();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }
}
