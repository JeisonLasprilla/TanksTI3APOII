package com.example.ti3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class CanvasController implements Initializable {

    @FXML
    private Canvas canvas;


    private GraphicsContext gc;
    private boolean isRunning = true;


    //Elementos gráficos
    private ArrayList<Player> players = new ArrayList<>();

    private Bullet bullet;
    private Map map;


    //Estados de las teclas
    boolean Wpressed, Apressed, Spressed, Dpressed, UPpresed, LEFTpressed, RIGHTpressed, DOWNpressed, SPACEpressed = false;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);


        Avatar player1 = new Avatar(canvas, "greenTank.png", new Vector(559,361),new Vector(3,3));
        Avatar player2 = new Avatar(canvas, "blueTank.png",new Vector(559,43),new Vector(3,3));
        Avatar CPU = new Avatar(canvas, "orangeTank.png", new Vector(40,43),new Vector(2,2));

        players.add(new Player(CPU)); //0
        players.add(new Player(player1)); //1
        players.add(new Player(player2)); //2

        int n = 78;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                players.get(i).getBulletStatuses()[j] = new BulletStatus(canvas, new Vector(n+18,40));
                n+=18;
            }
            n+= 60;
        }

        n = 78;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                players.get(i).getLives()[j] = new Life(canvas, new Vector(n+18,25));
                n+=18;
            }
            n+= 80;
        }

        bullet = new Bullet(canvas,"greenBullet.png", players.get(1).getAvatar().pos.x, players.get(1).getAvatar().pos.y, Vector.instanceOf(players.get(1).getAvatar().direction));
        map =  new Map(canvas, "map1.jpeg");
        draw();
    }

    private void onKeyReleased(KeyEvent keyEvent) {

        //Player1
        if(keyEvent.getCode() == KeyCode.W){
            Wpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.A){
            Apressed = false;
        }
        if(keyEvent.getCode() == KeyCode.S){
            Spressed = false;
        }
        if(keyEvent.getCode() == KeyCode.D){
            Dpressed = false;
        }

        //Player2
        if(keyEvent.getCode() == KeyCode.UP){
            UPpresed = false;
        }
        if(keyEvent.getCode() == KeyCode.LEFT){
            LEFTpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.DOWN){
            DOWNpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.RIGHT){
            RIGHTpressed = false;
        }

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if(keyEvent.getCode() == KeyCode.W){
            Wpressed = true;
        }
        if(keyEvent.getCode() == KeyCode.A){
            Apressed = true;
        }
        if(keyEvent.getCode() == KeyCode.S){
            Spressed = true;
        }
        if(keyEvent.getCode() == KeyCode.D){
            Dpressed = true;
        }
        if(keyEvent.getCode() == KeyCode.UP){
            UPpresed = true;
        }
        if(keyEvent.getCode() == KeyCode.LEFT){
            LEFTpressed = true;
        }
        if(keyEvent.getCode() == KeyCode.DOWN){
            DOWNpressed = true;
        }
        if(keyEvent.getCode() == KeyCode.RIGHT){
            RIGHTpressed = true;
        }

        if(keyEvent.getCode() == KeyCode.SPACE){
            SPACEpressed = true;
            bullet = new Bullet(canvas,"greenBullet.png", players.get(1).getAvatar().pos.x, players.get(1).getAvatar().pos.y, Vector.instanceOf(players.get(1).getAvatar().direction));
        }

    }

    public void moveCPU() {
        Vector endPosition = players.get(1).getAvatar().getPos();
        Vector endPosition2 = players.get(2).getAvatar().getPos();

        // Update is called once per frame
        double distance1 = Math.sqrt(Math.pow(endPosition.x-players.get(0).getAvatar().getPos().x,2) + Math.pow(endPosition.y-players.get(0).getAvatar().getPos().y,2));
        double distance2 = Math.sqrt(Math.pow(endPosition2.x-players.get(0).getAvatar().getPos().x,2) + Math.pow(endPosition2.y-players.get(0).getAvatar().getPos().y,2));
        if (distance2<distance1){
            endPosition = endPosition2;
        }

        if(Math.abs(players.get(0).getAvatar().getPos().x - endPosition.x)> 100 || Math.abs(players.get(0).getAvatar().getPos().y - endPosition.y ) > 100) {
            double x = endPosition.x - players.get(0).getAvatar().getPos().x;
            double y = endPosition.y - players.get(0).getAvatar().getPos().y;

            double abs = Math.abs(Math.tanh(y / x));
            if (y > -5 && y < 5){
                if (x > 0)
                    players.get(0).getAvatar().setAngle(0);
                else
                    players.get(0).getAvatar().setAngle(Math.PI);
            } else if (x > -5 && x < 5){
                if (y>0)
                    players.get(0).getAvatar().setAngle(Math.PI * 0.5);
                else
                    players.get(0).getAvatar().setAngle(Math.PI * 1.5);
            }else {

                if (x > 0 && y > 0)
                    players.get(0).getAvatar().setAngle(abs);
                else if (x < 0 && y < 0)
                    players.get(0).getAvatar().setAngle(Math.PI + abs);
                else if (x > 0)//And y < 0
                    players.get(0).getAvatar().setAngle(Math.PI * 1.5 + abs);
                else if (y > 0) //And  x < 0
                    players.get(0).getAvatar().setAngle(Math.PI - abs); //
            }
            players.get(0).getAvatar().moveForward();
        }
    }

    public void draw(){
        new Thread(
                ()->{
                    while(isRunning){
                        //Dibujo
                        Platform.runLater(()->{
                            gc.setFill(Color.DARKGRAY);
                            gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
                            map.draw();
                            players.get(1).getAvatar().draw();
                            players.get(2).getAvatar().draw();
                            players.get(0).getAvatar().draw();


                            for (BulletStatus current: players.get(1).getBulletStatuses()) {
                                if(current!=null){
                                    Label label = new Label("CPU");
                                    current.draw();
                                }
                            }

                            for (Life current: players.get(1).getLives()) {
                                if(current!=null){
                                    Label label = new Label("P1");
                                    current.draw();
                                }
                            }

                            for (BulletStatus current: players.get(2).getBulletStatuses()) {
                                if(current!=null){
                                    Label label = new Label("P2");
                                    current.draw();
                                }
                            }

                            for (Life current: players.get(2).getLives()) {
                                if(current!=null){
                                    current.draw();
                                }
                            }

                            for (BulletStatus current: players.get(0).getBulletStatuses()) {
                                if(current!=null){
                                    current.draw();
                                }
                            }

                            for (Life current: players.get(0).getLives()) {
                                if(current!=null){
                                    current.draw();
                                }
                            }

                            //CPU
                            moveCPU();

                            //Player 1
                            if(Wpressed){
                                players.get(1).getAvatar().moveForward();
                            }
                            if(Apressed){
                                players.get(1).getAvatar().changeAngle(-4);
                            }
                            if(Spressed){
                                players.get(1).getAvatar().moveBackward();
                            }
                            if(Dpressed){
                                players.get(1).getAvatar().changeAngle(4);
                            }

                            //Player 2
                            if(UPpresed){
                                players.get(2).getAvatar().moveForward();
                            }
                            if(LEFTpressed){
                                players.get(2).getAvatar().changeAngle(-4);
                            }
                            if(DOWNpressed){
                                players.get(2).getAvatar().moveBackward();
                            }
                            if(RIGHTpressed){
                                players.get(2).getAvatar().changeAngle(4);
                            }

                            //Shoot P1
                            if(SPACEpressed){
                                if (bullet!=null) {
                                    bullet.draw();
                                    bullet.move();

                                    double x = players.get(0).getAvatar().getPos().x - bullet.pos.x;
                                    double y = players.get(0).getAvatar().getPos().y - bullet.pos.y;


                                    if (y > -10 && y < 10 && x > -10 && x < 10) {
                                        //lives --1;
                                        System.out.println("-1");
                                        bullet = null;
                                    }
                                }
                            }


                        });
                        //Sleep
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();
    }

}
