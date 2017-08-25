package com.eveexite.coffeemaker_android.data.entity;

/**
 * Created by ivan on 6/1/17.
 */

public class CoffeeMakerEntity {

    private boolean turnOn;
    private boolean coffeeReady;
    private boolean coffeeMakerReady;
    private String timer;
    private String timerSleep;
    private int waterLevel;

    public CoffeeMakerEntity() {
    }

    //create setters for test


    public boolean getTurnOn() {
        return turnOn;
    }

    public void setTurnOn(boolean turnOn) {
        this.turnOn = turnOn;
    }

    public boolean getCoffeeReady() {
        return coffeeReady;
    }

    public void setCoffeeReady(boolean coffeeReady) {
        this.coffeeReady = coffeeReady;
    }

    public boolean getCoffeeMakerReady() {
        return coffeeMakerReady;
    }

    public void setCoffeeMakerReady(boolean coffeeMakerReady) {
        this.coffeeMakerReady = coffeeMakerReady;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getTimerSleep() {
        return timerSleep;
    }

    public void setTimerSleep(String timerSleep) {
        this.timerSleep = timerSleep;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

}
