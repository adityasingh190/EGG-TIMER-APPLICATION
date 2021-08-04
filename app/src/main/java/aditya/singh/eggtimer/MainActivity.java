package aditya.singh.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
     TextView timerTextView;
     boolean counterisactive=false;
     Button controllerButton;
     CountDownTimer countDownTimer;
    public void updateTimer(int secondsleft)
    {
        int minutes=(int)secondsleft/60;
        int seconds=secondsleft-minutes*60;
        String secondstring=Integer.toString(seconds);
        if(seconds<=9){
            secondstring="0"+secondstring;
        }

        timerTextView.setText(Integer.toString(minutes)+":"+secondstring);

    }
    public void controlButton(View view)
    {
        if(counterisactive==false)
        {
        counterisactive=true;
        timerSeekBar.setEnabled(false);
        controllerButton.setText("stop");
       countDownTimer= new CountDownTimer(timerSeekBar.getProgress() * 1000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
timerTextView.setText("0:00");
                MediaPlayer mplayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mplayer.start();
            }
        }.start();
    }else
        {
            timerTextView.setText("0:30");
            timerSeekBar.setProgress(30);
            countDownTimer.cancel();
            controllerButton.setText("Go!");
            timerSeekBar.setEnabled(true);
            counterisactive=false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controllerButton=(Button)findViewById(R.id.controllerbutton);
         timerSeekBar=(SeekBar)findViewById(R.id.eggseekbar);
        timerTextView=(TextView)findViewById(R.id.timer);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

updateTimer(progress);
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