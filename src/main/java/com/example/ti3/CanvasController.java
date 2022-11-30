package com.example.ti3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Avatar;
import model.Bullet;
import model.Map;
import model.Vector;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CanvasController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean isRunning = true;


    //Elementos gráficos
    private Avatar player1;

    private Avatar player2;

    private Avatar CPU;
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

        player1 = new Avatar(canvas, "greenTank.png", new Vector(559,361),new Vector(3,3));
        player2 = new Avatar(canvas, "blueTank.png",new Vector(559,43),new Vector(3,3));
        CPU = new Avatar(canvas, "orangeTank.png", new Vector(40,43),new Vector(2,2));
        bullet = new Bullet(canvas,"greenBullet.png", player1.pos.x, player1.pos.y, Vector.instanceOf(player1.direction));
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
            bullet = new Bullet(canvas,"greenBullet.png", player1.pos.x, player1.pos.y, Vector.instanceOf(player1.direction));
        }

    }

    public void moveCPU() {
        Vector endPosition = player1.getPos();
        Vector endPosition2 = player2.getPos();

        // Update is called once per frame
        double distance1 = Math.sqrt(Math.pow(endPosition.x-CPU.getPos().x,2) + Math.pow(endPosition.y-CPU.getPos().y,2));
        double distance2 = Math.sqrt(Math.pow(endPosition2.x-CPU.getPos().x,2) + Math.pow(endPosition2.y-CPU.getPos().y,2));
        if (distance2<distance1){
            endPosition = endPosition2;
        }

        if(Math.abs(CPU.getPos().x - endPosition.x)> 50 || Math.abs(CPU.getPos().y - endPosition.y ) > 50) {
            double x = endPosition.x - CPU.getPos().x;
            double y = endPosition.y - CPU.getPos().y;

            double abs = Math.abs(Math.tanh(y / x));
            if (y > -1 && y < 1){
                if (x > 0)
                    CPU.setAngle(0);
                else
                    CPU.setAngle(Math.PI);
            } else if (x > -1 && x < 1){
                if (y>0)
                    CPU.setAngle(Math.PI * 0.5);
                else
                    CPU.setAngle(Math.PI * 1.5);
            }else {

                if (x > 0 && y > 0)
                    CPU.setAngle(abs);
                else if (x < 0 && y < 0)
                    CPU.setAngle(Math.PI + abs);
                else if (x > 0)//And y < 0
                    CPU.setAngle(Math.PI * 1.5 + abs);
                else if (y > 0) //And  x < 0
                    CPU.setAngle(Math.PI - abs); //
            }
            CPU.moveForward();
        }
    }

    public void draw(){
        new Thread(
                ()->{
                    while(isRunning){
                        //Dibujo
                        Platform.runLater(()->{
                            gc.setFill(Color.BLACK);
                            gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
                            map.draw();
                            player1.draw();
                            player2.draw();
                            CPU.draw();

                            //CPU
                            moveCPU();

                            //Player 1
                            if(Wpressed){
                                player1.moveForward();
                            }
                            if(Apressed){
                                player1.changeAngle(-4);
                            }
                            if(Spressed){
                                player1.moveBackward();
                            }
                            if(Dpressed){
                                player1.changeAngle(4);
                            }

                            //Player 2
                            if(UPpresed){
                                player2.moveForward();
                            }
                            if(LEFTpressed){
                                player2.changeAngle(-4);
                            }
                            if(DOWNpressed){
                                player2.moveBackward();
                            }
                            if(RIGHTpressed){
                                player2.changeAngle(4);
                            }

                            //Shoot P1
                            if(SPACEpressed){
                                if (bullet!=null) {
                                    bullet.draw();
                                    bullet.move();

                                    double x = CPU.getPos().x - bullet.pos.x;
                                    double y = CPU.getPos().y - bullet.pos.y;


                                    if (y > -15 && y < 15 && x > -15 && x < 15) {
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
