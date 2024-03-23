package com.example.duckhunting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.content.Context;

import androidx.annotation.NonNull;

public class GameView extends View {
    private int height;
    private Paint paint;
    private Bitmap duck;
    private Rect duckRect;
    private Game game;
    private int[] TARGETS = {R.drawable.anim_duck0,R.drawable.anim_duck1,R.drawable.anim_duck2,R.drawable.anim_duck1};
    private Bitmap[] ducks;
    private int duckFrame;
    public static int DELTA_TIME = 100;

    public GameView(Context context, int width, int height){
        super(context);
        this.height=height;
        ducks = new Bitmap[TARGETS.length];
        for (int i = 0; i<ducks.length; i++)
            ducks[i]= BitmapFactory.decodeResource(getResources(),TARGETS[i]);
        float scale = ((float)width/(ducks[0].getWidth()*5));
        duckRect=new Rect(0, 0,width/5,(int)(ducks[0].getHeight()*scale));
        game = new Game(duckRect,0.05f);
        game.setDuckSpeed(width*0.00003f);
        game.setDeltaTime(DELTA_TIME);
        game.setHuntingRect( new Rect( 0, 0, width, height ) );
        game.setCannon(new Point(0, height), width/30, width/15, width/50);
        paint=new Paint();
        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(game.getBarrelRadius());
    }

    public Game getGame() {
        return game;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(game.getCannonCenter().x, game.getCannonCenter().y, game.getCannonRadius(), paint);
        canvas.drawLine(game.getCannonCenter().x,game.getCannonCenter().y,
                game.getCannonCenter().x+game.getBarrelLength()*(float)Math.cos(game.getCannonAngle()),
                game.getCannonCenter().y-game.getBarrelLength()*(float)Math.sin(game.getCannonAngle()),paint);

        duckFrame = ( duckFrame + 1 ) % ducks.length;
        canvas.drawBitmap( ducks[duckFrame], null, game.getDuckRect( ), paint );
    }
}
