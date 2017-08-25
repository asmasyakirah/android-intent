package com.asmasyakirah.android_intent;

import android.os.Environment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        TextView helloTextView = (TextView) findViewById(R.id.helloTextView);
        helloTextView.setText(String.valueOf(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)));
    }
}
