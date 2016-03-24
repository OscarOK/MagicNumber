package com.oscareduardo.magicnumber;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements KeyboardInterface {

    private TextView textViewNumber;
    Machine machine = new Machine();
    int possibleNumber;
    int magicNumber = machine.generateRandomNumber(100);
    String possible = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumber = (TextView) findViewById(R.id.textViewNumber);
    }

    @Override
    public void setButtonOne(View v) {
        possible += "1";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonTwo(View v) {
        possible += "2";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonThree(View v) {
        possible += "3";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonFour(View v) {
        possible += "4";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonFive(View v) {
        possible += "5";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonSix(View v) {
        possible += "6";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonSeven(View v) {
        possible += "7";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonEight(View v) {
        possible += "8";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonNine(View v) {
        possible += "9";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonZero(View v) {
        possible += "0";
        textViewNumber.setText(possible);
        possibleNumber = Integer.parseInt(possible);
    }

    @Override
    public void setButtonErase(View v) {
        possible = "0";
        textViewNumber.setText(possible);
    }

    @Override
    public void setButtonReady(View v) {
        boolean a = machine.checkMagicNumber(magicNumber, possibleNumber);

        if (a == false) {
            setButtonErase(v);
            machine.getWhere(magicNumber, possibleNumber, v);
        } else {
            Snackbar.make(v, "You won!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            magicNumber = machine.generateRandomNumber(100);
        }

        Log.e("Magic Number", "" + magicNumber);
        Log.e("Possible Number", "" + possibleNumber);
        Log.e("Boolean: ", "" + a);
    }
}
