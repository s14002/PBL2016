package jp.ac.it_college.std.s14002.android.csgo_simulator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        DatabaseOpenHelper helper = new DatabaseOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        //SQL
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from players", null);
        Cursor cursor = sqLiteDatabase.query("players", new String[]{"player_id as _id", "player_name"}, null, null, null, null, null);

        //adapterの準備
        //表示するカラム名
        String[] from = {"_id", "player_name"};
        //バインドするViewリソース
        int[] to = {android.R.id.text1, android.R.id.text2};

        //adapter生成
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1,cursor,from,to,0);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor,
                from, to, 0);

        //bindして表示
        listView.setAdapter(adapter);
        //クリックしたとき各行のデータを取得
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //各要素を取得
                String s1 = ((TextView)view.findViewById(android.R.id.text1)).getText().toString();
                String s2 = ((TextView)view.findViewById(android.R.id.text2)).getText().toString();

                Log.v("tama", "position=" + s1);
                Log.v("tama", "position=" + s2);
            }
        });

//        while (cursor.moveToNext()) {
//            Log.v("tama", cursor.getString(cursor.getColumnIndex("name")));
//        }

        Button button_detail = (Button) findViewById(R.id.button_detail);
        Button button_simple = (Button) findViewById(R.id.button_simple);
        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });

    }
}
