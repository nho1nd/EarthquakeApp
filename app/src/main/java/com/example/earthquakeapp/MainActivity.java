package com.example.earthquakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        mSearchResultsDisplay.setMovementMethod(new ScrollingMovementMethod());

        final String defaultDisplayText = mSearchResultsDisplay.getText().toString();

        mSearchButton.setOnClickListener(
                new View.OnClickListener(){ // a unnamed object
                    //inner method def
                    public void onClick(View v){
                        //get search string from user
                        String searchText = mSearchTermEditText.getText().toString();
                        // check if search string matches
                        // maybe I can use a dictionary?
                        searcher(searchText);
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

            String msg = searcher(mSearchTermEditText.getText().toString());
            startAboutActivityIntent.putExtra(Intent.EXTRA_TEXT, msg);

            startActivity(startAboutActivityIntent);
            Log.d("info", "More Info Activity launched");
        } // end if
        return true;
    } // end of onOptions
    public String searcher(String arg){
        CustomJSONParser JSONParser = new CustomJSONParser();
        Map <String, String > inputMap = new HashMap();
        //TODO: Tie in Android app input to execute .put command(s) below
        //Use below line as a template to look up things; more details in CustomJSONParser.java
        inputMap.put("place", arg);
        try{
            InputStream earthquakeData = getAssets().open("EarthquakeData.json");
            List<Feature> searchResult = JSONParser.readJsonWithObjectMapper(inputMap, earthquakeData);
            int i = 1;
            if (searchResult == null || searchResult.isEmpty()){
                mSearchResultsDisplay.append("\nNo such earthquake exists.");
            }else {
                String returnedPlace = "";
                for (Feature x : searchResult) {
                    mSearchResultsDisplay.append("\n\n``Search result " + i + ": \n\n" + x.toString() + "\n");
                    mSearchResultsDisplay.append(x.returnPlace());
                    i += 1;
                    returnedPlace = x.returnPlace();
                }
                return returnedPlace;
            }
        }catch(Exception e){
        }
        return " ";
    }

} // end of class

