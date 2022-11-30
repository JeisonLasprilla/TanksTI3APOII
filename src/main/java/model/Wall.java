package model;

import com.example.ti3.HelloApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Wall {
    private Canvas canvas;
    private GraphicsContext gc;
    private Vector pos;

    public Wall(Canvas canvas, double posx, double posy) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        pos =  new Vector(posx,posy);
    }

}
