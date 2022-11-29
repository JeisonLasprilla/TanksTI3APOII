package com.example.ti3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TextField nicknamePlayer1;

    @FXML
    private TextField nicknamePlayer2;

    @FXML
    private TextField nicknameCPU;

    @FXML
    void start(ActionEvent event) {

        UserName.getInstance().getUsernames()[0] = nicknameCPU.getText();
        UserName.getInstance().getUsernames()[1] = nicknamePlayer1.getText();
        UserName.getInstance().getUsernames()[2] = nicknamePlayer2.getText();
        System.out.println("CPU: "+UserName.getInstance().getUsernames()[0]+"\nPlayer 1:"+UserName.getInstance().getUsernames()[0]+"\nPlayer 2:"+UserName.getInstance().getUsernames()[0]);
        HelloApplication.showWindow("canvasView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nicknameCPU = new TextField("UNNAMED");
        nicknamePlayer1 = new TextField("UNNAMED");
        nicknamePlayer2 = new TextField("CPU");
    }
}