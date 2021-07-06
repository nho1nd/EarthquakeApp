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

        // grabbing the data that the originating intent sends us
        Intent intentThatStartedThisActivity = getIntent();
        //String message = "Error. Please input text for search.";
        //check is extra data
        //if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
        MainActivity obj = new MainActivity();

        final String message = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
        final String message2 = obj.searcher(message);
        mDisplayAboutTextView.append("\n" + message);
        mDisplayAboutTextView.append("\n" + message2);
        //} // end if
        //final String search_result =

        //for(int i=0; i < 2; i++) // where x is the size of the list containing your alphabet.
        //{
        //    Button button = new Button(this);
       //     button.setId(i);
        //    MoreInfoActivity.add(button);
       // }

        // open map button
        mOpenMapButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        openMap(message2);

                    } // end of onClick
                } // end of View
        ); // end of setOnCli
    } // end of onCreate

    public void openMap(String input_string){
        //String addressString = "University of Notre Dame, IN"; // if message is used as location
        Uri addressUri = Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", input_string).build();
        Intent openMapIntent = new Intent(Intent.ACTION_VIEW);
        openMapIntent.setData(addressUri);

        // check if it can be opened, and open it
        if(openMapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(openMapIntent);
        }
    } // end of open map

} // end of MoreInfoActivity class