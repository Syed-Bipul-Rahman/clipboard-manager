package com.clipboard.clipboardmanager;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button copybtn, pastebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of variables

        editText = findViewById(R.id.editText);
        copybtn = findViewById(R.id.copybtn);
        pastebtn = findViewById(R.id.pastebtn);


        copybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextData = editText.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Sample Text", editTextData);

                clipboardManager.setPrimaryClip(clipData);

                //clear edittext
                editText.getText().clear();

            }
        });

        pastebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String pasteData;
                //if data in not in clipboard
                if (!(clipboardManager.hasPrimaryClip())) {
                    Toast.makeText(MainActivity.this, "No Data in clipboard!", Toast.LENGTH_SHORT).show();
                }
                //if clipboard data is not plaintext
                else if (!(clipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {
                    Toast.makeText(MainActivity.this, "Data is not in plain text!", Toast.LENGTH_SHORT).show();
                } else {
                    ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                    pasteData = item.getText().toString();

                    //settext to edittext
                    editText.setText(pasteData);

                }

            }
        });

    }
}