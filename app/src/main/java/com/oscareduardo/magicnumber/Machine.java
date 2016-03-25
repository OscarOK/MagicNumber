package com.oscareduardo.magicnumber;

import android.support.design.widget.Snackbar;
import android.view.View;
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

    public void getWhere(int magicNumber, int possible, View v) {

        if (possible < magicNumber) {
            Snackbar.make(v, "Muy Pequeño", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (possible > magicNumber) {
            Snackbar.make(v, "Muy Grande", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
