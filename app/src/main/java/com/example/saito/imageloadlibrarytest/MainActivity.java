package com.example.saito.imageloadlibrarytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private CheckBox picassoCb;
    private CheckBox glideCb;
    private CheckBox frescoCb;
    private EditText createCountEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picassoCb = (CheckBox) findViewById(R.id.checkbox_picasso);
        glideCb = (CheckBox) findViewById(R.id.checkbox_glide);
        frescoCb = (CheckBox) findViewById(R.id.checkbox_fresco);
        createCountEt = (EditText) findViewById(R.id.editText_count);
        Button loadButton = (Button) findViewById(R.id.button_load);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });
    }

    private void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        // チェックボックスのチェック状態を取得
        intent.putExtra(Constants.PICASSO, picassoCb.isChecked());
        intent.putExtra(Constants.GLIDE, glideCb.isChecked());
        intent.putExtra(Constants.FRESCO, frescoCb.isChecked());
        intent.putExtra(Constants.CREATE_COUNT, createCountEt.getText().length() > 0 ? Integer.parseInt(createCountEt.getText().toString()) : 0);
        startActivity(intent);
    }
}
