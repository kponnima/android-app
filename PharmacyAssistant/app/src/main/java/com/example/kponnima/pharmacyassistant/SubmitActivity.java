package com.example.kponnima.pharmacyassistant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {

    TextView valuetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        valuetxt = (TextView) findViewById(R.id.disclaimertext);
        valuetxt.setMovementMethod(LinkMovementMethod.getInstance());
        Spannable spans = (Spannable) valuetxt.getText();
        ClickableSpan clickSpan = new ClickableSpan() {

            @Override
            public void onClick(View widget)
            {

                LayoutInflater inflater= LayoutInflater.from(SubmitActivity.this);
                View view=inflater.inflate(R.layout.activity_disclaimer, null);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SubmitActivity.this);
                alertDialog.setTitle("Pharmacy Assistant : Disclaimer Policy");
                alertDialog.setView(view);
                alertDialog.setPositiveButton("OK", null);
                AlertDialog alert = alertDialog.create();
                alert.show();
            }
        };
        spans.setSpan(clickSpan, spans.length()-5, spans.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /** Called when the user taps the SUBMIT button */
    public void displayConfirmation(View view) {
        Intent intent = new Intent(this, RecommendationActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the CANCEL button */
    public void displayCancelAlert(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(
                SubmitActivity.this);
        // Setting Dialog Title
        alert.setTitle("Attention !")
                .setCancelable(false)
                .setMessage("Are you sure you want to cancel ? All previous selections made would be lost and have to start over.")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        alert.show();
    }
}
