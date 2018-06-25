package com.example.kponnima.pharmacyassistant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {

    private static final String TAG = QuestionsActivity.class.getSimpleName();

    SeekBar sb;
    TextView valuetxt;
    Button next;
    Button previous;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    TextView displayQuestion;
    RadioGroup radioGroup;

    private Cursor AllQuestions;
    MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this);
    String Question1;
    String Question2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        sb = (SeekBar) findViewById(R.id.seekBar);
        valuetxt = (TextView) findViewById(R.id.progressText);
        next = (Button) findViewById(R.id.nextbutton);
        previous = (Button) findViewById(R.id.previousbutton);
        radioButton1 = (RadioButton)findViewById(R.id.radiooption1);
        radioButton2 = (RadioButton)findViewById(R.id.radiooption2);
        radioButton3 = (RadioButton)findViewById(R.id.radiooption3);
        displayQuestion = (TextView) findViewById(R.id.questiontext);
        radioGroup = (RadioGroup) findViewById(R.id.RGroup);

        //Show percentage progress made
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                valuetxt.setText("%Progress : "+String.valueOf(progress));
                setRecords();
                showRecords();
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb) {

            }

        });

        //Change the questions when user clicks on 'Next' button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "" + sb.getProgress());
                if((radioButton1.isChecked()) || (radioButton2.isChecked()) || (radioButton3.isChecked())){
                    sb.setProgress(sb.getProgress() + 20);
                    previous.setVisibility(View.VISIBLE);
                    Log.d(TAG, "" + sb.getProgress());

                    switch (sb.getProgress()) {
                        case 20:
                            radioButton1.setText(Question1);
                            radioButton2.setText(Question2);
                            radioButton3.setText(R.string.question3_display2);

                            displayQuestion.setText(R.string.display_message2);
                            radioGroup.clearCheck();
                            break;
                        case 80:
                            radioButton1.setText(R.string.question1_display2);
                            radioButton2.setText(R.string.question2_display2);
                            radioButton3.setText(R.string.question3_display2);

                            displayQuestion.setText(R.string.display_message2);
                            next.setText("Finish");
                            radioGroup.clearCheck();
                            break;
                        case 100:
                            Intent i = new Intent(getApplicationContext(), SubmitActivity.class);
                            startActivity(i);
                            break;
                        default:
                            radioButton1.setText("Question not set");
                            radioButton2.setText("Question not set");
                            radioButton3.setText("Question not set");

                            displayQuestion.setText("Question not set");
                            radioGroup.clearCheck();
                            break;
                    }
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            QuestionsActivity.this);
                    // Setting Dialog Title
                    alert.setTitle("Required !")
                            .setCancelable(false)
                            .setMessage("Please choose from one of the options displayed.")
                            .setNegativeButton("CANCEL",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                    alert.show();
                }
            }
        });

        //Change the questions when user clicks on 'Previous' button
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setProgress(sb.getProgress() - 20);
                switch (sb.getProgress()){
                    case 0:
                        previous.setVisibility(View.INVISIBLE);
                        radioButton1.setText(R.string.question1_display1);
                        radioButton2.setText(R.string.question2_display1);
                        radioButton3.setText(R.string.question3_display1);

                        displayQuestion.setText(R.string.display_message1);
                        break;
                    case 20:
                        radioButton1.setText(R.string.question1_display2);
                        radioButton2.setText(R.string.question2_display2);
                        radioButton3.setText(R.string.question3_display2);

                        displayQuestion.setText(R.string.display_message2);
                        break;
                    default:
                        radioButton1.setText("Question not set");
                        radioButton2.setText("Question not set");
                        radioButton3.setText("Question not set");

                        displayQuestion.setText("Question not set");
                        break;
                }

            }

        });
    }

    protected void setRecords() {
        databaseHelper.addQuestion("What now ?", 5);
        databaseHelper.addQuestion("what what", 6);
    }

    protected void showRecords() {
        AllQuestions = databaseHelper.getQuestions();
        AllQuestions.moveToFirst();
        Question1 = AllQuestions.getString(1);
        AllQuestions.moveToNext();
        Question2 = AllQuestions.getString(1);
        AllQuestions.close();
    }

    protected void moveNext() {
        if (!AllQuestions.isLast())
            AllQuestions.moveToNext();
        showRecords();
    }

    protected void movePrev() {
        if (!AllQuestions.isFirst())
            AllQuestions.moveToPrevious();
        showRecords();
    }
}