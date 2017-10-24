package com.example.bojana.lab_intents;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int EXPLICIT_CODE = 1;
    private static final int IMAGE_CODE = 12;
    private static final int ACTIVITY_PICKER = 123;
    private TextView textView;
    private Button buttonStartExplicit;
    private Button buttonImplicitActivity;
    private Button buttonSendTo;
    private Button buttonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        buttonStartExplicit = (Button) findViewById(R.id.activity_main_button_start_explicit);
        buttonImplicitActivity = (Button) findViewById(R.id.button_implicit_activity);
        buttonSendTo = (Button) findViewById(R.id.button_send_to);
        buttonImage = (Button) findViewById(R.id.button_select_image);


        buttonStartExplicit.setOnClickListener(this);
        buttonSendTo.setOnClickListener(this);
        buttonImage.setOnClickListener(this);
        buttonImplicitActivity.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_button_start_explicit:
                Intent intent = new Intent(this, ExplicitActivity.class);
                startActivityForResult(intent, EXPLICIT_CODE);
                break;
            case R.id.button_send_to:
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_TEXT, "Content send from " + MainActivity.class.getSimpleName());
                startActivity(Intent.createChooser(intent1, "Share with ..."));
                break;
            case R.id.button_select_image:
                Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent2.setType("image/*");
                startActivityForResult(Intent.createChooser(intent2, "Choose"), IMAGE_CODE);
                break;
            case R.id.button_implicit_activity:
                Intent intent3 = new Intent(Intent.ACTION_PICK_ACTIVITY);
                intent3.setAction(Intent.ACTION_MAIN);
                intent3.addCategory(Intent.CATEGORY_LAUNCHER);
                Intent intent4 = new Intent(intent3);
                startActivityForResult(Intent.createChooser(intent4, "Choose"), ACTIVITY_PICKER);


                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EXPLICIT_CODE) {
            Log.d("result", "result");
            if (resultCode == RESULT_OK) {
                textView.setText(data.getStringExtra("data"));

            }
        }
        if (requestCode == IMAGE_CODE) {
            if (data != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(data.getDataString()), "image/*");
                startActivity(Intent.createChooser(intent, "Open image with ..."));
            }
        }
        if (requestCode == ACTIVITY_PICKER) {
            if (data != null) {
                Intent intent = new Intent();
                // data.getComponent();
                //Log.d("component", data.getComponent().toString());
                intent.setComponent(data.getComponent());
                startActivity(intent);
            }
        }
    }
}
