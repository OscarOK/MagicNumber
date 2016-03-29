package com.oscareduardo.magicnumber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FragmentHelper extends ContextWrapper {

    private AlertDialog alertDialog;
    private Button buttonAccept, buttonLeaderboard;
    private TextView textViewTries;

    public FragmentHelper(Context base) {
        super(base);
    }

    public AlertDialog createLoginDialogo(Activity activity, int tries) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());

        LayoutInflater inflater = activity.getLayoutInflater();

        View v = inflater.inflate(R.layout.victory_fragment, null);

        builder.setView(v);

        buttonAccept = (Button) v.findViewById(R.id.buttonAgain);
        buttonLeaderboard = (Button) v.findViewById(R.id.buttonLeaderboard);
        textViewTries = (TextView) v.findViewById(R.id.textViewTries);

        String textTries = getResources().getString(R.string.tries) + " " + tries;

        textViewTries.setText(textTries);

        alertDialog = builder.create();

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        buttonLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return alertDialog;
    }

}
