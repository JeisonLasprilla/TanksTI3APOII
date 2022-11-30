package model;

public class Player {

    private Avatar avatar;
    private BulletStatus bulletStatuses[] = new BulletStatus[6];
    private Life lives[] = new Life[5];

    public Player(Avatar avatar) {
        this.avatar = avatar;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public BulletStatus[] getBulletStatuses() {
        return bulletStatuses;
    }

    public void setBulletStatuses(BulletStatus[] bulletStatuses) {
        this.bulletStatuses = bulletStatuses;
    }

    public Life[] getLives() {
        return lives;
    }

    public void setLives(Life[] lives) {
        this.lives = lives;
    }
}
