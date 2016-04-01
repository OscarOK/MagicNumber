package com.oscareduardo.magicnumber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentHelper extends ContextWrapper {

    AlertDialog alertDialog;
    Machine machine = new Machine();

    public FragmentHelper(Context base) {
        super(base);
    }

    public void createCustomToast(LayoutInflater layoutInflater, ViewGroup viewGroup, String phrase,
                                  int idBackground, int idImage, int time) {

        LayoutInflater inflater = layoutInflater;
        View layout = inflater.inflate(R.layout.message_toast, viewGroup);
        layout.setBackgroundResource(idBackground);

        ImageView image = (ImageView) layout.findViewById(R.id.imageViewToastImage);
        image.setImageResource(idImage);

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(phrase);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(time);
        toast.setView(layout);
        toast.show();
    }

    public AlertDialog createVictoryFragment(final Activity activity, int tries, long time, SharedPreferences sharedPreferences) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());

        LayoutInflater inflater = activity.getLayoutInflater();

        View v = inflater.inflate(R.layout.victory_fragment, null);

        builder.setView(v);

        TextView textViewTries = (TextView) v.findViewById(R.id.textViewTries);
        TextView textViewTime = (TextView) v.findViewById(R.id.textViewTime);
        TextView textViewBoardTries = (TextView) v.findViewById(R.id.textViewLessTries);
        TextView textViewBoardTime = (TextView) v.findViewById(R.id.textViewBestTime);

        String textTime = getResources().getString(R.string.time) + ": " + machine.makeTime(time) + "s";
        String textTries = getResources().getString(R.string.tries) + ": " + tries;

        textViewTries.setText(textTries);
        textViewTime.setText(textTime);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        long sPTimeElapse = sharedPreferences.getLong("bestTime", -1);

        if (time <= sPTimeElapse && sPTimeElapse > 0 || sPTimeElapse == 0) {
            editor.putLong("bestTime", time);
            editor.putInt("lessTries", tries);
            editor.apply();
        }

        sPTimeElapse = sharedPreferences.getLong("bestTime", time++);
        int sPTries = sharedPreferences.getInt("lessTries", 0);

        textTime = machine.makeTime(sPTimeElapse) + "s";
        textTries = "" + sPTries;

        textViewBoardTime.setText(textTime);
        textViewBoardTries.setText(textTries);

        Button buttonAgain = (Button) v.findViewById(R.id.buttonAgain);

        alertDialog = builder.create();

        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentHelper.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }

}
