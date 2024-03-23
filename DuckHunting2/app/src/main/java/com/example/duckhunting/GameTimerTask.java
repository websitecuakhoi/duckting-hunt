package com.example.duckhunting;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {
    private Game game;
    private GameView gameView;

    public GameTimerTask(GameView view){
        this.gameView=view;
        game = view.getGame();
        game.startDuckFromRightTopHalf();
    }
    public void run(){
        game.moveDuck();
        if (game.duckOffScreen())
            game.startDuckFromRightTopHalf();
        gameView.postInvalidate();
    }
}
