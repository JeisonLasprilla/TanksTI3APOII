package model;

import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Wall {
    private Canvas canvas;
    private GraphicsContext gc;
    double initX;
    double initY;
    double finalX;
    double finalY;
    public Wall(Canvas canvas, double initX, double initY, double finalX, double finalY) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        this.initX = initX;
        this.finalX = finalX;
        this.initY = initY;
        this.finalY = finalY;
    }

    public Bounds getBounds() {
        Rectangle r = new  Rectangle(initX, initY, finalX, finalY);
        //Rectangle r = new  Rectangle(i, 20, 600, 20);
        return r.getLayoutBounds();
    }

    public boolean borderColision(Avatar player){
        return player.getBounds().intersects(getBounds());
    }

}
