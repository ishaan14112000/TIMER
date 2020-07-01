package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView counter;
    SeekBar timerseekbar;
    Boolean counteractive=false;
    Button startb;
    CountDownTimer countDownTimer;
    public void restart(){
        counter.setText("0:30");
        timerseekbar.setProgress(30);
        timerseekbar.setEnabled(true);
        countDownTimer.cancel();
        startb.setText("START");
        counteractive=false;

    }
    public void starttimer(View view){
        if(counteractive){
            restart();
        }
        else {
            counteractive = true;
            timerseekbar.setEnabled(false);
            startb.setText("STOP");
             countDownTimer = new CountDownTimer(timerseekbar.getProgress() * 1000 + 100, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {
                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.fischio_rigore);
                    mediaPlayer.start();
                    restart();

                }
            }.start();
        }
    }
    public void update(int secondsleft){
        int minutes=secondsleft / 60;
        int seconds=secondsleft - (minutes*60);
        String secondString=Integer.toString(seconds);
        if(seconds<=9){
            secondString="0"+secondString;
        }
        counter.setText(Integer.toString(minutes)+" : "+secondString);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerseekbar= findViewById(R.id.TimerSeekBar);
        counter= findViewById(R.id.timer);
        startb=findViewById(R.id.start);

        timerseekbar.setMax(600);
        timerseekbar.setProgress(30);

        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
