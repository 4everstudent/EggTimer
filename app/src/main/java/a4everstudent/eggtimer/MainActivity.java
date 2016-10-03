package a4everstudent.eggtimer;

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

                //update the timer text view
                timerView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));


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
