package com.example.harapekoyuki.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderChange extends AppCompatActivity implements View.OnClickListener{

    private Button button_change;
    private Button button_return;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_change);

        button_change = (Button) findViewById(R.id.button_change);
        button_change.setOnClickListener(this);

        button_return = (Button) findViewById(R.id.button_return);
        button_return.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.button_change) {
            intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, 0);
        } else if (v.getId() == R.id.button_return) {
            intent = new Intent(this, OrderStatus.class);
            startActivityForResult(intent, 1);
        }

    }
}
