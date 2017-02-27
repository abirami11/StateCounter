package com.example.balaji.statecounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.key;
import static android.R.attr.value;
import static java.lang.reflect.Array.getInt;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    int counter = 0;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView3);
        mTextView.setText(String.valueOf(counter));
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTextView = (TextView) findViewById(R.id.textView3);
                counter = Integer.parseInt(mTextView.getText().toString());
                mTextView.setText(String.valueOf(++counter));
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences settings = getSharedPreferences("MyPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        // Necessary to clear first if we save preferences onPause.
        editor.clear();
        editor.putInt("Counter", counter);
        editor.commit();
    }
    @Override
    protected void onResume(){
        super.onResume();

        mTextView = (TextView) findViewById(R.id.textView3);
        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        int count = pref.getInt("Counter", 0);
        Log.d("hello", String.valueOf(count));
        mTextView.setText(String.valueOf(count));

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mTextView = (TextView) findViewById(R.id.textView3);
        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        mTextView.setText(String.valueOf(pref.getInt("Counter", 0)));
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Counter", counter);
        editor.commit();
    }

}
