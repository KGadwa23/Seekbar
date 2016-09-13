package com.katrina.seekbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SquareActivity extends AppCompatActivity {

    public static final String EXTRA_INSIDE_SQUARE = "com.katrina.tapthesquare.inside_square";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);

        //Get a reference to the Intent that launched this activity
        Intent launchIntent = getIntent();
        //Get the Extra that we added to the Intent in SeekBarActivity
        //Required to specify a default value if the Extra is not found. 100 seems ok default?
        int squareSize = launchIntent.getIntExtra(SeekBarActivity.EXTRA_SQUARE_SIZE, 100);

        //Get a reference to ImageView and draw a square in it
        ImageView squareView = (ImageView) findViewById(R.id.square);

        ShapeDrawable square = new ShapeDrawable(new RectShape());
        square.setIntrinsicHeight(squareSize);
        square.setIntrinsicWidth(squareSize);

        square.getPaint().setColor(Color.CYAN);    //or Color.BLUE or Color.YELLOW or...

        squareView.setImageDrawable(square);

        //TODO add listener for user tapping the square, or tapping outside the square

        //if the user taps the square, return to SeekBarAcitivity;
        //provide information that user did tap the square
        squareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an Intent that will carry data back to launching Activity
                Intent resultIntent = new Intent();
                //Put the value true as an Extra, using a key to name the Extra
                resultIntent.putExtra(EXTRA_INSIDE_SQUARE, true);
                //Set the result to be OK, and also provide the Inent that has our Extra
                setResult(RESULT_OK, resultIntent);
                //Close this Activity, and send result back to launching Activity
                finish();
            }
        });

        //What is the user didn't tap the square? Add a listener to the main content view
        //So if the user taps anywhere else, this listner will handle the tap event
        //The provess is the same, exept will send the valus false for the extra.
        View mainView = findViewById(android.R.id.content);
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_INSIDE_SQUARE, false);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });



    }
}
