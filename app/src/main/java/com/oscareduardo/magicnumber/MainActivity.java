package com.oscareduardo.magicnumber;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Machine machine = new Machine();
    private FragmentHelper fragmentHelpers = new FragmentHelper(this);
    private long timeElapsed;
    private int possibleNumber, magicNumber, tries;
    private String possible, time, toastText;
    private RelativeLayout relativeLayoutNumbers;
    private TextView textViewNumber, textViewPreviousNumber;
    private Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix,
            buttonSeven, buttonEight, buttonNine, buttonZero;
    private ImageButton buttonDelete, buttonReady, buttonErase;
    private Chronometer chronometer;
    private ViewGroup viewGroup;
    private Animation animation;
    boolean firstTime;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewGroup = (ViewGroup) findViewById(R.id.toast_layout_root);

        sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);

        firstTime = sharedPreferences.getBoolean("firtsTime", true);

        toastText = getString(R.string.welcome);
        fragmentHelpers.createCustomToast(getLayoutInflater(), viewGroup, toastText,
                R.drawable.toast_background_welcome, R.drawable.ic_sentiment_satisfied, 1000);

        relativeLayoutNumbers = (RelativeLayout) findViewById(R.id.relativeLayoutNumbers);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        textViewNumber = (TextView) findViewById(R.id.textViewNumber);
        textViewPreviousNumber = (TextView) findViewById(R.id.textViewPreviousNumber);
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

        startGame(true);

        fT(firstTime);
    }

    public void fT(boolean switchFT) {

        if (switchFT) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.e("Shared Preferences", "VIRGIN");
            editor.putLong("bestTime", 0);
            editor.putInt("lessTires", 0);
            firstTime = false;
            editor.putBoolean("firtsTime", firstTime);
            editor.apply();
        } else {
            Log.e("Shared Preferences", "Not VIRGIN");
        }
    }

    public void startGame(boolean switchChronometer) {
        magicNumber = machine.generateRandomNumber(100);
        Log.e("Magic Number", "" + magicNumber);
        textViewNumber.setText("");
        textViewPreviousNumber.setVisibility(View.GONE);
        buttonErase.setVisibility(View.GONE);
        tries = 1;
        chronometer.setBase(SystemClock.elapsedRealtime());
        if (switchChronometer) chronometer.start();
    }

    @Override
    public void onClick(View v) {
        int idButton = v.getId();
        possible = textViewNumber.getText().toString();
        timeElapsed = (SystemClock.elapsedRealtime() - chronometer.getBase());

        if (buttonErase.getVisibility() == View.GONE &&
                textViewNumber.getText().toString().isEmpty()) {
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            buttonErase.setVisibility(View.VISIBLE);
            buttonErase.startAnimation(animation);
        }

        try {
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
                    if (buttonErase.getVisibility() == View.VISIBLE) {
                        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.fade_out);
                        buttonErase.startAnimation(animation);
                        buttonErase.setVisibility(View.GONE);
                    }
                    textViewNumber.setText("");
                    break;

                case R.id.buttonReady:
                    possibleNumber = Integer.parseInt(possible);
                    boolean check = machine.checkMagicNumber(magicNumber, possibleNumber);
                    String textPreviousNumber = getString(R.string.previousNumber) +
                            " " +
                            possibleNumber;

                    animation = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fade_in);
                    textViewPreviousNumber.setVisibility(View.VISIBLE);
                    textViewPreviousNumber.setText(textPreviousNumber);
                    textViewPreviousNumber.startAnimation(animation);

                    if (check) {
                        chronometer.stop();

                        fragmentHelpers.createVictoryFragment(this, tries, timeElapsed, sharedPreferences).show();
                        startGame(false);
                    } else {
                        tries++;
                        textViewNumber.setText("");

                        if (possibleNumber < magicNumber) {
                            toastText = getString(R.string.toSmall);
                            fragmentHelpers.createCustomToast(getLayoutInflater(), viewGroup,
                                    toastText, R.drawable.toast_background_small,
                                    R.drawable.ic_arrow_downward, 250);
                        } else if (possibleNumber > magicNumber) {
                            toastText = getString(R.string.toBig);
                            fragmentHelpers.createCustomToast(getLayoutInflater(), viewGroup,
                                    toastText, R.drawable.toast_background_big,
                                    R.drawable.ic_arrow_upward, 250);
                        }
                    }

                    break;
            }
        } catch (Exception e) {
            Log.e("Switch", "ERROR");
        }
    }
}
