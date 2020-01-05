package com.sistempintar.app.myquiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class play extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private boolean timeHasStarted = true;
    public TextView text;
    private final long startTime = 20*1000;
    private final long interval = 1*1000;

    int skor = 0;
    int batas=0;
    int pr = 0;
    int randomQuestionIndex;

    Button botButton, topButton, botButton2, topButton2;
    TextView resultTextView, mtvSkor;
    TextView soalTextView;

    ArrayList<TrueFalseQuestion> questions = new ArrayList<TrueFalseQuestion>();
    ArrayList<Integer> norepeat = new ArrayList<>(11);

    TrueFalseQuestion currentQuestion;

    boolean topTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        for (int i=0;i<10;i++){
            norepeat.add(i);
        }
        botButton = findViewById(R.id.botButton);
        topButton = findViewById(R.id.topButton);
        botButton2 = findViewById(R.id.botButton2);
        topButton2 = findViewById(R.id.topButton2);

        resultTextView = findViewById(R.id.resultTextView);
        soalTextView = findViewById(R.id.soalTextView);

        mtvSkor = (TextView) findViewById(R.id.tvSkor);
        resultTextView.setText("");
        initializeQuestion();

        text = findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(startTime,interval);
        text.setText(text.getText()+String.valueOf(startTime/1000));
        if (timeHasStarted==true){
            countDownTimer.start();
        }
        else if (timeHasStarted==false){
            countDownTimer.cancel();
        }
    }
    public void initializeQuestion()
    {

        CommunicationAsynctask com = new CommunicationAsynctask();
        String response = "";
        try {
            response = com.execute("http://www.bukasoal.id/ganz/get_questions.php?qty=15").get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        questions = TrueFalseQuestion.parseJSONArray(response);

        generateQuestion();
    }
    public void tampil_soal(){

        soalTextView.setText(currentQuestion.soal);
    }

    public void generateQuestion()
    {
        if (batas>=10){
            norepeat.add(0);
            mtvSkor.setText(""+skor);
            String jumlahskor = String.valueOf(skor);
            Intent i = new Intent(play.this, HasilPenilaian.class);
            i.putExtra("skorAkhir", jumlahskor);
            i.putExtra("activity","TrueFalseQuestion");
            play.this.finish();
            startActivity(i);
        }
        else{
            batas++;
        }
        pr = (int)(Math.random()*(norepeat.size()-1));
        randomQuestionIndex = norepeat.get(pr);
        currentQuestion = questions.get((randomQuestionIndex+1)%10);
        tampil_soal();
        mtvSkor.setText(""+skor);
        if (Math.random() > 0.5)
        {
            topTrue = true;
            skor = skor +2;
            topButton.setText(currentQuestion.trueStatement);
            botButton.setText(currentQuestion.falseStatement);
            topButton2.setText(currentQuestion.falseStatement2);
            botButton2.setText(currentQuestion.falseStatement3);

        }
        else
        {
            topTrue = false;
            topButton2.setText(currentQuestion.falseStatement3);
            botButton2.setText(currentQuestion.falseStatement2);
            topButton.setText(currentQuestion.falseStatement);
            botButton.setText(currentQuestion.trueStatement);
        }
        norepeat.remove(pr);

    }

    public void setTopButtonClicked(View e)
    {
        if (topTrue == true)
        {
            resultTextView.setText("Jawaban Benar");
        }
        else
        {
            resultTextView.setText("SALAH!");

        }
        generateQuestion();
    }

    public void setTopButton2Clicked(View e)
    {
        if (topTrue == false)
        {

            resultTextView.setText("Jawaban Benar");
        }
        else
        {
            resultTextView.setText("Jawaban Salah");
        }
        generateQuestion();
    }

    public void setBotButton2Clicked(View e)
    {
        if (topTrue == false)
        {
            resultTextView.setText("Jawaban Benar");
        }
        else
        {
            resultTextView.setText("Jawaban Salah");

        }
        generateQuestion();
    }

    public void setBotButtonClicked(View e)
    {
        if (topTrue == false)
        {
            resultTextView.setText("Jawaban Benar");
        }
        else
        {
            resultTextView.setText("Jawaban Salah");

        }
        generateQuestion();
    }

    class MyCountDownTimer extends CountDownTimer{
        public MyCountDownTimer(long startTime, long interval){
            super(startTime,interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText(""+millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            String jumlahskor = String.valueOf(skor);
            Intent i = new Intent(play.this, HasilPenilaian.class);
            i.putExtra("skorAkhir", jumlahskor);
            i.putExtra("activity","TrueFalseQuestion");
            play.this.finish();
            startActivity(i);
        }
    }
}
