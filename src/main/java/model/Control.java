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

    private InfoPlayer infoPlayers[] = new InfoPlayer[3];

    public InfoPlayer[] getPlayers() {
        return infoPlayers;
    }
}
