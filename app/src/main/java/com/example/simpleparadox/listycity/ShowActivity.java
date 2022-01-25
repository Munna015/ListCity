package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    Button back_button;
    TextView city_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        back_button = (Button) findViewById(R.id.back_btn_id);
        city_text_view = (TextView) findViewById(R.id.city_text_view_id);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            city_text_view.setText(bundle.getString("city_name"));
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}