package com.zey.simpleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView questionLabel, questionCountLabel, scoreLabel;
    EditText answerEdt;
    Button submitButton;
    ProgressBar progressBar;
    ArrayList<Question> questionArrayList;
    int currentPosition = 0;
    int numberOfCorrectAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionCountLabel = findViewById(R.id.numQ);
        questionLabel = findViewById(R.id.question);
        scoreLabel = findViewById(R.id.score);
        answerEdt = findViewById(R.id.ans);
        submitButton = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progress);
        questionArrayList = new ArrayList<>();

        setUpQuestion();

        setData();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();
            }
        });

        answerEdt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                Log.e("event.getAction()",event.getAction()+"");
                Log.e("event.keyCode()",keyCode+"");
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    checkAnswer();
                    return true;
                }
                return false;
            }
        });

    }
    public void checkAnswer(){
        String answerString  = answerEdt.getText().toString().trim();




        if(answerString.equalsIgnoreCase(questionArrayList.get(currentPosition).getAnswer())){
            numberOfCorrectAnswer ++;



            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("Right Asswer")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            currentPosition ++;

                            setData();
                            answerEdt.setText("");
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();


        }else {

            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Wrong Answer")
                    .setContentText("The right answer is : "+questionArrayList.get(currentPosition).getAnswer())
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();

                            currentPosition ++;

                            setData();
                            answerEdt.setText("");
                        }
                    })
                    .show();
        }





        int x = ((currentPosition+1) * 100) / questionArrayList.size();

        progressBar.setProgress(x);



    }




    public void setUpQuestion(){


        questionArrayList.add(new Question(" 1/1 = ","1"));
        questionArrayList.add(new Question(" 2*9 = ","18"));
        questionArrayList.add(new Question(" 9*9 = ","81"));
        questionArrayList.add(new Question(" 4*5 = ","20"));
        questionArrayList.add(new Question(" 23*3 = ","69"));



    }

    public void setData(){


        if(questionArrayList.size()>currentPosition) {

            questionLabel.setText(questionArrayList.get(currentPosition).getQuestion());

            scoreLabel.setText("Score :" + numberOfCorrectAnswer + "/" + questionArrayList.size());
            questionCountLabel.setText("Question No : " + (currentPosition + 1));


        }else{


            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("You have successfully completed the quiz")
                    .setContentText("Your score is : "+ numberOfCorrectAnswer + "/" + questionArrayList.size())
                    .setConfirmText("Restart")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog.dismissWithAnimation();
                            currentPosition = 0;
                            numberOfCorrectAnswer = 0;
                            progressBar.setProgress(0);
                            setData();
                        }
                    })
                    .setCancelText("Close")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog.dismissWithAnimation();
                            finish();
                        }
                    })
                    .show();

        }

    }

}
