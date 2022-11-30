package model;

public class Control {

    //CPU [0]
    //Player1 [1]
    //Player2 [2]

    private static Control instance;

    public static Control getInstance(){
        if(instance == null){
            instance = new Control();
        }
        return instance;
    }

    private Player players [] = new Player[3];

    public Player[] getPlayers() {
        return players;
    }
}
