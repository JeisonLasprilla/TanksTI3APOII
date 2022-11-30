package com.example.ti3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Control;
import model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TextField nicknameP1;

    @FXML
    private TextField nicknameP2;

    @FXML
    private TextField nicknameCPU;

    @FXML
    void start(ActionEvent event) {

        if(!nicknameCPU.getText().equals("CPU")){
            Control.getInstance().getPlayers()[0].setName(nicknameCPU.getText());
        }

        if(!nicknameP1.getText().equals("")){
            Control.getInstance().getPlayers()[1].setName(nicknameP1.getText());
        }

        if(!nicknameP2.getText().equals("")){
            Control.getInstance().getPlayers()[2].setName(nicknameP2.getText());
        }

        System.out.println("CPU: "+ Control.getInstance().getPlayers()[0].getName()+"\nPlayer 1:"+ Control.getInstance().getPlayers()[1].getName()+"\nPlayer 2:"+ Control.getInstance().getPlayers()[2].getName());
        HelloApplication.showWindow("canvasView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Control.getInstance().getPlayers()[0] = new Player("CPU", 0);
        Control.getInstance().getPlayers()[1] = new Player("UNNAMED", 0);
        Control.getInstance().getPlayers()[2] = new Player("UNNAMED", 0);
    }
}