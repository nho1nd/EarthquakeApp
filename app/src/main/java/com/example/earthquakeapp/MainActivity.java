package com.example.earthquakeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private TextView mTitleApp;
    private TextView mSearchResultsDisplay;
    private EditText mSearchTermEditText;
    private Button mSearchButton;
    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect with the visual elements in activity_main.xml
        mTitleApp = (TextView) findViewById(R.id.tv2_display_text);
        mSearchResultsDisplay = (TextView) findViewById(R.id.tv_display_text);
        mSearchTermEditText = (EditText) findViewById(R.id.et_search_box);
        mSearchButton = (Button) findViewById(R.id.search_button);
        mResetButton = (Button) findViewById(R.id.reset_button);

        final String[] list_cities = {"Hawaii", "California"};

        final String defaultDisplayText = mSearchResultsDisplay.getText().toString();

        mSearchButton.setOnClickListener(
                new View.OnClickListener(){ // a unnamed object
                    //inner method def
                    public void onClick(View v){
                        //get search string from user
                        String searchText = mSearchTermEditText.getText().toString();
                        // check if search string matches
                        // maybe I can use a dictionary?
                        for(String input_string : list_cities){
                            if(input_string.toLowerCase().equals(searchText.toLowerCase())){
                                mSearchResultsDisplay.setText("Information: ");
                                mSearchResultsDisplay.setText(input_string);
                                // display information on Earthquake
                                break;
                            }else{
                                mSearchResultsDisplay.setText("No results match.");
                            }
                        }
                    } // end of onClick method

                } // end of View.OnClickListener
        ); // end of setOnClickListener

        mResetButton.setOnClickListener(
                new View.OnClickListener(){ // a unnamed object
                    //inner method def
                    public void onClick(View v){
                        // reset the text
                        mSearchResultsDisplay.setText(defaultDisplayText);

                    } // end of onClick method

                } // end of View.OnClickListener
        ); // end of setOnClickListener


    } // end of onCreate

    // how to connect with menu
    // Steps: go to About page from menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu); // id of the menu resource that should be inflated
        return true;
    } // end of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuItemSelected = item.getItemId();

        if(menuItemSelected == R.id.menu_contact){ // id from main_menu.xml for the About item


            //spl - launching activity in our app - then launch the About Activity
            Class destinationActivity = MoreInfoActivity.class;

            // create intent to go to next page
            Intent startAboutActivityIntent = new Intent(MainActivity.this, destinationActivity);

            String msg = mSearchTermEditText.getText().toString();
            startAboutActivityIntent.putExtra(Intent.EXTRA_TEXT, msg);

            startActivity(startAboutActivityIntent);
            Log.d("info", "More Info Activity launched");
        } // end if
        return true;
    } // end of onOptions

} // end of class

