package model;

import javafx.scene.image.Image;

public class Life {

    private Image image;
    private Vector pos;

    public Life(Image image, Vector pos) {
        this.image = image;
        this.pos = pos;
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
