package a4everstudent.eggtimer;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


// TODO: 05-10-2016 Add pause function
// TODO: 05-10-2016 run test to check device compatibility

public class MainActivity extends AppCompatActivity {
    //define initialTime in seconds
    int maxTime = 60*60;
    int currentTime= 30;
    SeekBar timerControl;
    TextView timerView;
    MediaPlayer mPlayer;
    Boolean counterIsActive = false;
    Button controllerButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timerView.setText("00:30");
        timerControl.setProgress(30);
        timerControl.setEnabled(true);
        countDownTimer.cancel();
        controllerButton.setText("GO!");
        counterIsActive = false;
    }

    public void controlTimer(View view) {
        if (counterIsActive == false) {
            counterIsActive = true;
            timerControl.setEnabled(false);
            controllerButton.setText("STOP!");


            countDownTimer = new CountDownTimer(timerControl.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerView.setText("00:00");
                    mPlayer.start();
                    resetTimer();
                }
            }.start();
        }

        else{
            resetTimer();
        }
    }

    public void updateTimer(int secondsLeft){
        //convert time in minutes and seconds
        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft - minutes *60;

        String secondsString = Integer.toString(seconds);

        if(secondsString.length() == 1){

            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                    && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                secondsString = "0"+secondsString;
            }
            else{
                secondsString = String.format("%02d", secondsString);
            }
        }

        //update the timer text view
        timerView.setText(Integer.toString(minutes)+":"+ secondsString);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerControl = (SeekBar) findViewById(R.id.seekBar);
        timerView = (TextView) findViewById(R.id.timerView);
        timerControl.setMax(maxTime);
        timerControl.setProgress(currentTime);

        mPlayer = MediaPlayer.create(this, R.raw.air_horn_from_soundbible);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        timerControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //create countdown timer
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //stop countdown timer
            }
        });


    }

}
