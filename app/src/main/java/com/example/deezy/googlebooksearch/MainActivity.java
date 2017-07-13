package com.example.deezy.googlebooksearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.example.deezy.googlebooksearch.R.id.RB1;
import static com.example.deezy.googlebooksearch.R.id.RBG;

public class MainActivity extends AppCompatActivity {
    public String BOOK_REQUEST_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void searchFunc(View view){
        EditText input = (EditText) findViewById(R.id.input);
        CharSequence userInput = input.getText();
        String userInputString = userInput.toString();
        userInputString = userInputString.replace(" ", "-");

        RadioButton RB1 = (RadioButton) findViewById(R.id.RB1);
        Boolean title = RB1.isChecked();
        RadioButton RB2 = (RadioButton) findViewById(R.id.RB2);
        Boolean author = RB2.isChecked();
        RadioButton RB3 = (RadioButton) findViewById(R.id.RB3);
        Boolean publisher = RB3.isChecked();


        if(title){
            BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q="+ userInputString +"&intitle&maxResults=10";
        }else if(author){
            BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q="+ userInputString +"&inauthor&maxResults=10";
        }else{
            BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=" + userInputString + "&inpublisher&maxResults=10";
        }
        Log.i("URL",BOOK_REQUEST_URL);
        Intent NQIntent = new Intent(MainActivity.this, ResultsActivity.class);
        NQIntent.putExtra("BOOKER",BOOK_REQUEST_URL);
        startActivity(NQIntent);

    }
}
