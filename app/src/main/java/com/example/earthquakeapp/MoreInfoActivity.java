package com.example.earthquakeapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MoreInfoActivity extends AppCompatActivity {

    private TextView mDisplayAboutTextView;
    private Button mOpenMapButton;

    MainActivity obj = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        // connect with UI elements
        mDisplayAboutTextView = (TextView) findViewById(R.id.tv_about_text);
        mOpenMapButton = (Button) findViewById(R.id.button_open_map);

        // Grabbing the coordinates of the FIRST result
        Intent intentThatStartedThisActivity = getIntent();
        MainActivity obj = new MainActivity();

        final String message = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
        final String message2 = obj.searcher(message);
        mDisplayAboutTextView.append("\n" + message);
        mDisplayAboutTextView.append("\n" + message2);

        // open map button
        mOpenMapButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        openMap(message);

                    } // end of onClick
                } // end of View
        ); // end of setOnCli
    } // end of onCreate

    public void openMap(String inputString){
        //String addressString = "St. Louis, MO"; // if message is used as location
        Uri addressUri = Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", inputString).build();
        Intent openMapIntent = new Intent(Intent.ACTION_VIEW);
        openMapIntent.setData(addressUri);

        // check if it can be opened, and open it
        if(openMapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(openMapIntent);
        }
    } // end of open map

} // end of MoreInfoActivity class