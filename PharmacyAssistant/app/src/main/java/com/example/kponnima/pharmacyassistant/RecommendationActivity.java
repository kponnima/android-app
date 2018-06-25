package com.example.kponnima.pharmacyassistant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RecommendationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
    }

    /** Called when the user taps the AVAILABILITYT button */
    public void displayAvailability(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                RecommendationActivity.this);
        // Setting Dialog Title
        alert.setTitle("Available !")
                .setCancelable(false)
                .setMessage("The product is available in : Aisle 12")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
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

    /** Called when the user taps the SAVE button */
    public void displayRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the PRINT button */
    public void displayPrintPage(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                RecommendationActivity.this);
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

    /** Called when the user taps the DONE button */
    public void displayFinishAlert(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                RecommendationActivity.this);
        // Setting Dialog Title
        alert.setTitle("Attention !")
                .setCancelable(false)
                .setMessage("Are you sure you want to proceed ? You are now navigating away from the page and data would be lost.")
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
