package com.asmasyakirah.android_intent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity
{
    String sendAction;

    private static final String SEND_IN          = "com.asmasyakirah.android_intent.sendIn";
    private static final String SEND_IN_RESPOND  = "com.asmasyakirah.android_intent.sendInRespond";
    private static final String SEND_OUT         = "com.asmasyakirah.android_intent.sendOut";
    private static final String SEND_OUT_RESPOND = "com.asmasyakirah.android_intent.sendOutRespond";

    private static final int SEND_IN_REQUEST  = 0;
    private static final int SEND_OUT_REQUEST = 1;

    EditText sendInput;
    Button sendInButton, sendInRespondButton, sendOutButton, sendOutRespondButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setupUI();

        /*
        TextView helloTextView = (TextView) findViewById(R.id.helloTextView);
        helloTextView.setText(String.valueOf(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)));
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SEND_IN_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                String respondData = data.getStringExtra("DATA");
                showMessage("OK! Respond: " + respondData);
            }
            if (resultCode == RESULT_CANCELED)
            {
                showMessage("Cancelled");
            }
            else if ((int)resultCode == 1)
            {
                String err = data.getStringExtra("ERRORMSG");
                showMessage("Error: " + err);
            }
        }
    }

    private void setupUI()
    {
        setContentView(R.layout.activity_send);

        sendInput = (EditText) findViewById(R.id.sendInput);
        sendInButton = (Button) findViewById(R.id.sendInButton);
        sendInRespondButton = (Button) findViewById(R.id.sendInRespondButton);
        sendOutButton = (Button) findViewById(R.id.sendOutButton);
        sendOutRespondButton = (Button) findViewById(R.id.sendOutRespondButton);

        // Button click respond
        sendInButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                sendAction = SEND_IN;
                setIntentData();
            }
        });
        sendInRespondButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                sendAction = SEND_IN_RESPOND;
                setIntentData();
            }
        });
        sendOutButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                sendAction = SEND_OUT;
                setIntentData();
            }
        });
        sendOutRespondButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                sendAction = SEND_OUT_RESPOND;
                setIntentData();
            }
        });
    }

    private void setIntentData()
    {
        if (!sendInput.getText().toString().equals(""))
        {
            Intent intent;

            switch (sendAction)
            {
                case SEND_IN:
                    intent = new Intent(this, ReceiveActivity.class);
                    intent.setAction(sendAction);
                    intent.putExtra("DATA", sendInput.getText().toString());
                    startActivity(intent);
                    break;

                case SEND_IN_RESPOND:
                    intent = new Intent(this, ReceiveActivity.class);
                    intent.setAction(sendAction);
                    intent.putExtra("DATA", sendInput.getText().toString());
                    startActivityForResult(intent, SEND_IN_REQUEST);
                    break;

                case SEND_OUT:
                    intent = new Intent();
                    intent.setAction(sendAction);
                    intent.putExtra("DATA", sendInput.getText().toString());
                    try
                    {
                        startActivity(intent);
                    }
                    catch (Exception ex)
                    {
                        showMessage(ex.getMessage());
                    }
                    break;

                case SEND_OUT_RESPOND:
                    break;
            }
        }
        else
        {
            showMessage("Please key in data");
        }
    }

    private void showMessage(String message)
    {
        //Toast.MakeText(this, message, ToastLength.Short).Show();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(message);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // Do nothing
            }
        });
        dialog.show();
    }
}
