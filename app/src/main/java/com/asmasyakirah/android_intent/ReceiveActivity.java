package com.asmasyakirah.android_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveActivity extends AppCompatActivity
{
    Intent intent;
    String receivedAction;
    String receivedFrom;
    String receivedData;

    TextView receivedFromTextView;
    TextView receivedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        setupUI();
        getIntentData();
    }

    private void setupUI()
    {
        receivedFromTextView = (TextView) findViewById(R.id.receivedFromTextView);
        receivedTextView = (TextView) findViewById(R.id.receivedTextView);
    }

    public void getIntentData()
    {
        intent = getIntent();
        receivedFrom = "Received data";
        if (intent != null)
        {
            receivedAction = intent.getAction();
            receivedFrom = receivedFrom + " from " + receivedAction;
            receivedData = intent.getStringExtra("DATA");
        }
        else
        {
            receivedAction = "";
            receivedData = "";
        }

        setOutput();
    }

    private void setOutput()
    {
        receivedFromTextView.setText(receivedFrom);
        receivedTextView.setText(receivedData);
    }

    private void showMessage(String message)
    {
        Toast.makeText(ReceiveActivity.this, message, Toast.LENGTH_SHORT).show();
        //showNotice(NOTICE_NO_INTERNET);
    }
}
