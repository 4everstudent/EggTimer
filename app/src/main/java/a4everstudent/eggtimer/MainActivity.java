package a4everstudent.eggtimer;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;



// TODO: 01-10-2016 connect button

public class MainActivity extends AppCompatActivity {
    //define initialTime in seconds
    int maxTime = 60*60;
    int currentTime= 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerControl = (SeekBar) findViewById(R.id.seekBar);
        final TextView timerView = (TextView) findViewById(R.id.timerView);
        timerControl.setMax(maxTime);
        timerControl.setProgress(currentTime);


        timerControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //read the value that the user choose and update the text view

                //convert time in minutes and seconds
                int minutes = (int) progress/60;
                int seconds = progress - minutes *60;

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
