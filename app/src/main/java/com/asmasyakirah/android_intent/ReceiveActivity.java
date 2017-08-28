package com.asmasyakirah.android_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveActivity extends AppCompatActivity
{
    private static final String RECEIVE_IN          = "com.asmasyakirah.android_intent.sendIn";
    private static final String RECEIVE_IN_RESPOND  = "com.asmasyakirah.android_intent.sendInRespond";
    private static final String RECEIVE_OUT         = "com.asmasyakirah.xamarin_intent.sendOut";
    private static final String RECEIVE_OUT_RESPOND = "com.asmasyakirah.xamarin_intent.sendOutRespond";

    Intent intent;
    String receivedAction;
    String receivedFrom;
    String receivedData;

    TextView receivedFromTextView;
    TextView receivedTextView;
    LinearLayout respondLayout;
    EditText respondInput;
    Button okButton;
    Button cancelButton;

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
        setIntent();
    }

    private void setupUI()
    {
        receivedFromTextView = (TextView) findViewById(R.id.receivedFromTextView);
        receivedTextView = (TextView) findViewById(R.id.receivedTextView);
        respondLayout = (LinearLayout) findViewById(R.id.respondLayout);
        respondInput = (EditText) findViewById(R.id.respondInput);
        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
    }

    public void getIntentData()
    {
        intent = getIntent();

        if (intent != null)
        {
            receivedAction = intent.getAction();
            receivedFrom = "Received data";
            receivedData = intent.getStringExtra("DATA");

            switch (receivedAction)
            {
                case RECEIVE_IN:
                    break;

                case RECEIVE_IN_RESPOND:
                    // Visible respond layout
                    respondLayout.setVisibility(View.VISIBLE);
                    break;

                case RECEIVE_OUT:
                    receivedFrom = receivedFrom + " from " + receivedAction;
                    break;

                case RECEIVE_OUT_RESPOND:
                    receivedFrom = receivedFrom + " from " + receivedAction;
                    break;
            }
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

    private void setIntent()
    {
        final Intent respondIntent = new Intent();

        // Button click respond.
        okButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                respondIntent.putExtra("DATA", respondInput.getText().toString());
                setResult(RESULT_OK, respondIntent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                setResult(RESULT_CANCELED, respondIntent);
                finish();
            }
        });
    }

    private void showMessage(String message)
    {
        Toast.makeText(ReceiveActivity.this, message, Toast.LENGTH_SHORT).show();
        //showNotice(NOTICE_NO_INTERNET);
    }
}
