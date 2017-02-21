package jp.ac.it_college.std.s14002.android.csgo_simulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView teamName1 = (TextView) findViewById(R.id.text1);
        TextView teamName2 = (TextView) findViewById(R.id.text2);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("TeamName1");
        String name2 = intent.getStringExtra("TeamName2");

        teamName1.setText(name1);
        teamName2.setText(name2);

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
