package com.example.bojana.lab_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExplicitActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button voRed;
    private Button otkazi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        editText = (EditText) findViewById(R.id.editText);
        voRed = (Button) findViewById(R.id.button_voRed);
        otkazi = (Button) findViewById(R.id.buttonOtkazi);


        otkazi.setOnClickListener(this);
        voRed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOtkazi:
                finish();
                break;
            case R.id.button_voRed:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        if(!editText.getText().toString().trim().isEmpty() || editText.getText().toString().trim().length() > 0){
            Intent intent = new Intent();
            intent.putExtra("data",editText.getText().toString());
            setResult(RESULT_OK,intent);
            super.onBackPressed();
        }else {
            Toast.makeText(ExplicitActivity.this,"Input some text",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            return true;
        }
        return false;
    }
}
