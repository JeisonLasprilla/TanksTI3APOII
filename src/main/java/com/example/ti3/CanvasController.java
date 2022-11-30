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


    //Estados de las teclas
    boolean Wpressed, Apressed, Spressed, Dpressed, UPpresed, LEFTpressed, RIGHTpressed, DOWNpressed = false;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);

        player1 = new Avatar(canvas, "greenTank.png", new Vector(559,361),new Vector(3,3));
        player2 = new Avatar(canvas, "blueTank.png",new Vector(559,43),new Vector(3,3));
        CPU = new Avatar(canvas, "orangeTank.png", new Vector(40,43),new Vector(2,2));
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
    }

    public void moveCPU() {
        Vector endPosition = player1.getPos();
        Vector endPosition2 = player2.getPos();

        // Update is called once per frame
        if(Math.abs(CPU.getPos().x - endPosition.x)> 5 || Math.abs(CPU.getPos().y - endPosition.y ) > 5) {
            double x = endPosition.x - CPU.getPos().x;
            double y = endPosition.y - CPU.getPos().y;

            double abs = Math.abs(Math.tanh(y / x));
            if (y > -5 && y < 5){
                if (x > 0)
                    CPU.setAngle(0);
                else
                    CPU.setAngle(Math.PI);
            } else if (x > -5 && x < 5){
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
                                player1.changeAngle(-3);
                            }
                            if(Spressed){
                                player1.moveBackward();
                            }
                            if(Dpressed){
                                player1.changeAngle(3);
                            }

                            //Player 2
                            if(UPpresed){
                                player2.moveForward();
                            }
                            if(LEFTpressed){
                                player2.changeAngle(-3);
                            }
                            if(DOWNpressed){
                                player2.moveBackward();
                            }
                            if(RIGHTpressed){
                                player2.changeAngle(3);
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
