package com.asmasyakirah.android_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ReceiveActivity extends AppCompatActivity
{
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

        Intent intent = getIntent();
        if (intent != null)
        {
            showMessage("Received intent");
            String action = intent.getAction();
            String type = intent.getType();
            showMessage(action);
            showMessage(type);
        }
        else
        {
            showMessage("No intent received");
        }
    }

    private void showMessage(String message)
    {
        Toast.makeText(ReceiveActivity.this, message, Toast.LENGTH_SHORT).show();
        //showNotice(NOTICE_NO_INTERNET);
    }

}
