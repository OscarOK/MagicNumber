package com.oscareduardo.magicnumber;

import java.util.Random;

public class Machine {

    public int generateRandomNumber(int max) {
        Random random = new Random();
        int magicNumber;

        magicNumber = random.nextInt(max) + 1;

        return magicNumber;
    }

    public boolean checkMagicNumber(int magicNumber, int possible) {
        boolean itIs;

        if (possible == magicNumber) itIs = true;
        else itIs = false;

        return itIs;
    }

    public String eraseOne(String string) {
        if (string != null && string.length() > 0) {
            string = string.substring(0, string.length()-1);
        }

        return string;
    }

    public String makeTime(long timeElapsed) {
        String time;

        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        if (minutes < 10) time = "0" + minutes;
        else time = "" + minutes;

        if (seconds < 10) time += ":0" + seconds;
        else time += ":" + seconds;

        return time;
    }
}
