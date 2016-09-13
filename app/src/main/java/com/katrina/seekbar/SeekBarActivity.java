package com.katrina.seekbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SeekBarActivity extends AppCompatActivity {

    public static final String EXTRA_SQUARE_SIZE = "come.katrina.tapthesquare.squaresize";
    private static final int SQUARE_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        final TextView seekbarValueTV = (TextView) findViewById(R.id.seek_bar_value_label);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);

        Button showSquare = (Button) findViewById(R.id.display_square_button);

        showSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchSquareActivity = new Intent(SeekBarActivity.this, SquareActivity.class);
                //Add data to the Intent using putExtra method
                launchSquareActivity.putExtra(EXTRA_SQUARE_SIZE, seekBar.getProgress());
                startActivityForResult(launchSquareActivity, SQUARE_REQUEST_CODE);
            }
        });

        int seekbarProgress = seekBar.getProgress();

        seekbarValueTV.setText("The intital SeekBar value is " + seekbarProgress);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //The onProgressChanged method supplies the current SeekBar progress
                seekbarValueTV.setText("The seekbar value is " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Don't need to respond to this event, but still need to implement this method
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //same here
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        //Need to check what request ended up with this result
        //and need to verify if the result was ok-and not hte user cancelling, or pressing back button

        if (requestCode == SQUARE_REQUEST_CODE && resultCode == RESULT_OK) {

            //Get the data from the Intent. Again, must provide a default value if the key is not found
            boolean tappedSquare = data.getBooleanExtra(SquareActivity.EXTRA_INSIDE_SQUARE, false);

            //Indicate result to user with a Toast
            if (tappedSquare) {
                Toast.makeText(this, "You tapped the square!", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "Sorry, you missed the square", Toast.LENGTH_LONG).show();
        }
    }
}
