package com.oscareduardo.magicnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewNumber, textViewPreviousNumber;
    private Machine machine = new Machine();
    private int possibleNumber, magicNumber, tries;
    private String possible;
    private Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix,
            buttonSeven, buttonEight, buttonNine, buttonZero;
    private ImageButton buttonDelete, buttonReady, buttonErase;
    private RelativeLayout relativeLayoutNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumber = (TextView) findViewById(R.id.textViewNumber);
        textViewPreviousNumber = (TextView) findViewById(R.id.textViewPreviousNumber);

        magicNumber = machine.generateRandomNumber(100);
        Log.i("Magic Number", "" + magicNumber);

        final TextView tv = (TextView) findViewById(R.id.textViewDeveloper);

        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.demo);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "OSCAR", Toast.LENGTH_SHORT).show();
                animation.setDuration(2000);
                tv.startAnimation(animation);
            }
        });

        tries = 1;

        relativeLayoutNumbers = (RelativeLayout) findViewById(R.id.relativeLayoutNumbers);

        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonOne.setOnClickListener(this);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonTwo.setOnClickListener(this);
        buttonThree = (Button) findViewById(R.id.buttonThree);
        buttonThree.setOnClickListener(this);
        buttonFour = (Button) findViewById(R.id.buttonFour);
        buttonFour.setOnClickListener(this);
        buttonFive = (Button) findViewById(R.id.buttonFive);
        buttonFive.setOnClickListener(this);
        buttonSix = (Button) findViewById(R.id.buttonSix);
        buttonSix.setOnClickListener(this);
        buttonSeven = (Button) findViewById(R.id.buttonSeven);
        buttonSeven.setOnClickListener(this);
        buttonEight = (Button) findViewById(R.id.buttonEight);
        buttonEight.setOnClickListener(this);
        buttonNine = (Button) findViewById(R.id.buttonNine);
        buttonNine.setOnClickListener(this);
        buttonZero = (Button) findViewById(R.id.buttonZero);
        buttonZero.setOnClickListener(this);
        buttonErase = (ImageButton) findViewById(R.id.buttonErase);
        buttonErase.setOnClickListener(this);
        buttonDelete = (ImageButton) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);
        buttonReady = (ImageButton) findViewById(R.id.buttonReady);
        buttonReady.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int idButton = v.getId();
        possible = textViewNumber.getText().toString();
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.demo);
    animation.setDuration(1000);
        textViewPreviousNumber.startAnimation(animation);

        try {

            if (possible.isEmpty()) {
                buttonErase.setVisibility(View.GONE);
                textViewPreviousNumber.setVisibility(View.GONE);
            } else {
                buttonErase.setVisibility(View.VISIBLE);
                textViewPreviousNumber.setVisibility(View.VISIBLE);
            }

            switch (idButton) {
                case R.id.buttonOne:
                    textViewNumber.setText(possible + "1");
                    break;

                case R.id.buttonTwo:
                    textViewNumber.setText(possible + "2");
                    break;

                case R.id.buttonThree:
                    textViewNumber.setText(possible + "3");
                    break;

                case R.id.buttonFour:
                    textViewNumber.setText(possible + "4");
                    break;

                case R.id.buttonFive:
                    textViewNumber.setText(possible + "5");
                    break;

                case R.id.buttonSix:
                    textViewNumber.setText(possible + "6");
                    break;

                case R.id.buttonSeven:
                    textViewNumber.setText(possible + "7");
                    break;

                case R.id.buttonEight:
                    textViewNumber.setText(possible + "8");
                    break;

                case R.id.buttonNine:
                    textViewNumber.setText(possible + "9");
                    break;

                case R.id.buttonZero:
                    if (!possible.isEmpty())
                    textViewNumber.setText(possible + "0");
                    break;

                case R.id.buttonErase:
                    possible = machine.eraseOne(possible);
                    textViewNumber.setText(possible);
                    break;

                case R.id.buttonDelete:
                    textViewNumber.setText("");

                case R.id.buttonReady:
                    possibleNumber = Integer.parseInt(textViewNumber.getText().toString());

                    boolean check = machine.checkMagicNumber(magicNumber, possibleNumber);

                    textViewPreviousNumber.setText(
                            getString(R.string.previousNumber) +
                                    " " +
                                    possibleNumber);

                    if (!check) {
                        textViewNumber.setText("");
                        machine.getWhere(magicNumber, possibleNumber, v);
                        tries++;
                    } else {
                        textViewNumber.setText("");
                        textViewPreviousNumber.setText(getString(R.string.previousNumber));

                        FragmentHelper fragmentHelpers = new FragmentHelper(this);

                        fragmentHelpers.createLoginDialogo(this, tries).show();
                        machine.youWonMessage(v);
                        magicNumber = machine.generateRandomNumber(100);
                        tries = 1;
                        buttonErase.setVisibility(View.GONE);
                        relativeLayoutNumbers.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));
                    }

                    Log.i("Magic Number", "" + magicNumber);
                    Log.i("Possible Number", "" + possibleNumber);
                    Log.i("Boolean", "" + check);
                    break;
            }
        } catch (Exception e) {
            Log.e("Switch", "ERROR");
        }
    }
}
