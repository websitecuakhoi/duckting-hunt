package com.example.duckhunting;

import android.graphics.Rect;
import android.graphics.Point;
import android.util.Log;

import java.util.Random;

public class Game {
    private Rect huntingRect;
    private int deltaTime;
    //Manage Duck
    private Rect duckRect;
    private int duckWidth;
    private int duckHeight;
    private float duckSpeed;
    private boolean duckShot;

    //Manage Canon
    private Point cannonCenter;
    private int cannonRadius;
    private int barrelLength;
    private int barrelRadius;
    private float cannonAngle;
    private Point buletCenter;
    private int nuletRadius;
    pri

    private Random random;
    public Game(Rect duckRect, float duckSpeed){
        setDuckRect(duckRect);
        setDuckSpeed(duckSpeed);
        setDuckShot(false);
        random = new Random();
    }
    public Rect getDuckRect(){
        return duckRect;
    }
    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
    }

    public void setDuckHeight(int duckHeight) {
        this.duckHeight = duckHeight;
    }

    public void setDuckRect(Rect duckRect) {
        this.duckRect = duckRect;
        setDuckWidth(duckRect.right-duckRect.left);
        setDuckHeight(duckRect.bottom-duckRect.top);
    }

    public void setDuckShot(boolean duckShot) {
        this.duckShot = duckShot;
    }

    public void setDuckSpeed(float duckSpeed) {
        this.duckSpeed = duckSpeed;
    }

    public void setDuckWidth(int duckWidth) {
        this.duckWidth = duckWidth;
    }

    public boolean isDuckShot() {
        return duckShot;
    }

    public float getDuckSpeed() {
        return duckSpeed;
    }

    public int getDeltaTime() {
        return deltaTime;
    }

    public int getDuckHeight() {
        return duckHeight;
    }

    public int getDuckWidth() {
        return duckWidth;
    }

    public void setHuntingRect(Rect huntingRect) {
        this.huntingRect = huntingRect;
    }

    public Rect getHuntingRect() {
        return huntingRect;
    }

    public Point getCannonCenter() {
        return cannonCenter;
    }
    public int getCannonRadius(){
        return cannonRadius;
    }

    public int getBarrelLength() {
        return barrelLength;
    }

    public int getBarrelRadius() {
        return barrelRadius;
    }

    public float getCannonAngle() {
        return cannonAngle;
    }

    public void setCannonAngle(float cannonAngle) {
        if (cannonAngle>=0 && cannonAngle <= Math.PI/2)
            this.cannonAngle = cannonAngle;
        else if (cannonAngle < 0)
            this.cannonAngle = 0;
        else
            this.cannonAngle = (float)Math.PI/2;
    }
    public void setCannon(Point cannonCenter, int cannonRadius, int barrelLength, int barrelRadius){
        if (cannonCenter!=null && cannonRadius>0 &&barrelLength>0){
            this.cannonCenter = cannonCenter;
            this.cannonRadius = cannonRadius;
            this.barrelLength = barrelLength;
            this.barrelRadius = barrelRadius;
        }
    }
    public void startDuckFromRightTopHalf(){
        duckRect.left=huntingRect.right;
        duckRect.right=duckRect.left+duckWidth;
        duckRect.top= random.nextInt(huntingRect.bottom/2);
        duckRect.bottom=duckRect.top+duckHeight;
    }

    public void moveDuck(){
        duckRect.left -= duckSpeed * deltaTime;
        duckRect.right -= duckSpeed * deltaTime;
        //Log.w("moveDuck","left="+getDuckRect().left);
    }
    public boolean duckOffScreen(){
        return duckRect.right<0 || duckRect.left > huntingRect.right;
    }
}
