package jp.ac.it_college.std.s14002.android.csgo_simulator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SelectActivity extends AppCompatActivity {
    private Spinner lSpinner;
    private Spinner mSpinner;
    private Spinner nSpinner;

    private String spinnerItems2[] = {"Best of", "1", "2", "3", "5"}; //そのまま使う

    private String spinnerItems3[] = {"Map", "dust2", "inferno", "nuke", "train", "mirage", "cache", "cobblestone", "overpass"};
    private TextView textView1;
    private TextView textView2;
    private String bestOf;
    private String mapName1;
    private String mapName2;
    private String mapName3;
    private String mapName4;
    private String mapName5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        textView1 = (TextView) findViewById(R.id.text_team_name_1);
        textView2 = (TextView) findViewById(R.id.text_team_name_2);
        lSpinner = (Spinner) findViewById(R.id.spinner1);
        mSpinner = (Spinner) findViewById(R.id.spinner2);
        nSpinner = (Spinner) findViewById(R.id.spinner3);
        final DatabaseOpenHelper helper = new DatabaseOpenHelper(this);
        final SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        //SQL
        final Cursor cursor1 = sqLiteDatabase.rawQuery("select team_id as _id, team_name from teams", null);
        //adapterの準備
        //表示するカラム名
        final String[] from = {"team_name"};
        //バインドするViewリソース
        final int[] to = {R.id.text1};


        //TeamName1 Adapter生成
        final SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this, R.layout.spinner_item, cursor1,
                from, to, 0);
        //ドロップダウンリストのレイアウトを設定します。
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown);
        //spinnerにadapter1をセットbindして表示
        lSpinner.setAdapter(adapter1);
        //クリックしたとき各行のデータを取得
        lSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                Cursor cursor = (Cursor) spinner.getSelectedItem();
                String team_name = cursor.getString(cursor.getColumnIndex("team_name"));
                textView1.setText(team_name);
            }
            public void onNothingSelected(AdapterView parent) {
            }
        });

        //TeamName2 adapter生成
        SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this, R.layout.spinner_item, cursor1,
                from, to, 0);
        //ドロップダウンリストのレイアウトを設定します。
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown);
        //spinnerにadapter1をセットbindして表示
        mSpinner.setAdapter(adapter2);
        //クリックしたとき各行のデータを取得
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                Cursor cursor = (Cursor) spinner.getSelectedItem();
                String team_name = cursor.getString(cursor.getColumnIndex("team_name"));
                textView2.setText(team_name);
            }
            public void onNothingSelected(AdapterView parent) {
            }
        });

        //BestOf adapter生成
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerItems2);
        adapter3.setDropDownViewResource(R.layout.spinner_dropdown);
        // spinnerにadapter3をセット
        nSpinner.setAdapter(adapter3);
        //Listenerを登録
        nSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                Spinner nSpinner = (Spinner) parent;
                final String item3 = (String) nSpinner.getSelectedItem();

                Spinner m1Spinner;
                Spinner m2Spinner;
                Spinner m3Spinner;
                Spinner m4Spinner;
                Spinner m5Spinner;

                //SQL
                final Cursor cursor2 = sqLiteDatabase.rawQuery("select map_id as _id, map_name from map", null);
                //表示するカラム名
                final String[] from2 = {"map_name"};
                //バインドするViewリソース
                final int[] to2 = {R.id.text1};


                //adapter生成
                final SimpleCursorAdapter adapter4 = new SimpleCursorAdapter(SelectActivity.this, R.layout.spinner_item, cursor2,
                        from2, to2, 0);
                adapter4.setDropDownViewResource(R.layout.spinner_dropdown);

                final SimpleCursorAdapter adapter5 = new SimpleCursorAdapter(SelectActivity.this, R.layout.spinner_item, cursor2,
                        from2, to2, 0);
                adapter5.setDropDownViewResource(R.layout.spinner_dropdown);

                final SimpleCursorAdapter adapter6 = new SimpleCursorAdapter(SelectActivity.this, R.layout.spinner_item, cursor2,
                        from2, to2, 0);
                adapter6.setDropDownViewResource(R.layout.spinner_dropdown);

                final SimpleCursorAdapter adapter7 = new SimpleCursorAdapter(SelectActivity.this, R.layout.spinner_item, cursor2,
                        from2, to2, 0);
                adapter7.setDropDownViewResource(R.layout.spinner_dropdown);

                final SimpleCursorAdapter adapter8 = new SimpleCursorAdapter(SelectActivity.this, R.layout.spinner_item, cursor2,
                        from2, to2, 0);
                adapter8.setDropDownViewResource(R.layout.spinner_dropdown);

                if (item3.equals("1")) {
                    m1Spinner = (Spinner) findViewById(R.id.spinner4);
                    adapter4.setDropDownViewResource(R.layout.spinner_dropdown);
                    m1Spinner.setAdapter(adapter4);
                    m1Spinner.setVisibility(View.VISIBLE);

                    m2Spinner = (Spinner) findViewById(R.id.spinner5);
                    adapter5.setDropDownViewResource(R.layout.spinner_dropdown);
                    m2Spinner.setAdapter(adapter5);
                    m2Spinner.setVisibility(View.INVISIBLE);

                    m3Spinner = (Spinner) findViewById(R.id.spinner6);
                    adapter6.setDropDownViewResource(R.layout.spinner_dropdown);
                    m3Spinner.setAdapter(adapter6);
                    m3Spinner.setVisibility(View.INVISIBLE);

                    m4Spinner = (Spinner) findViewById(R.id.spinner7);
                    adapter7.setDropDownViewResource(R.layout.spinner_dropdown);
                    m4Spinner.setAdapter(adapter7);
                    m4Spinner.setVisibility(View.INVISIBLE);

                    m5Spinner = (Spinner) findViewById(R.id.spinner8);
                    adapter8.setDropDownViewResource(R.layout.spinner_dropdown);
                    m5Spinner.setAdapter(adapter8);
                    m5Spinner.setVisibility(View.INVISIBLE);

                    //Listenerを登録
                    m1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName1 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                } else if (item3.equals("2")) {

                    m1Spinner = (Spinner) findViewById(R.id.spinner4);
                    m1Spinner.setAdapter(adapter4);
                    m1Spinner.setVisibility(View.VISIBLE);


                    m2Spinner = (Spinner) findViewById(R.id.spinner5);
                    m2Spinner.setAdapter(adapter5);
                    m2Spinner.setVisibility(View.VISIBLE);

                    m3Spinner = (Spinner) findViewById(R.id.spinner6);
                    m3Spinner.setAdapter(adapter6);
                    m3Spinner.setVisibility(View.INVISIBLE);

                    m4Spinner = (Spinner) findViewById(R.id.spinner7);
                    m4Spinner.setAdapter(adapter7);
                    m4Spinner.setVisibility(View.INVISIBLE);

                    m5Spinner = (Spinner) findViewById(R.id.spinner8);
                    m5Spinner.setAdapter(adapter8);
                    m5Spinner.setVisibility(View.INVISIBLE);

                    //Listenerを登録
                    m1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName1 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName2 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                } else if (item3.equals("3")) {

                    m1Spinner = (Spinner) findViewById(R.id.spinner4);
                    m1Spinner.setAdapter(adapter4);
                    m1Spinner.setVisibility(View.VISIBLE);


                    m2Spinner = (Spinner) findViewById(R.id.spinner5);
                    m2Spinner.setAdapter(adapter5);
                    m2Spinner.setVisibility(View.VISIBLE);

                    m3Spinner = (Spinner) findViewById(R.id.spinner6);
                    m3Spinner.setAdapter(adapter6);
                    m3Spinner.setVisibility(View.VISIBLE);

                    m4Spinner = (Spinner) findViewById(R.id.spinner7);
                    m4Spinner.setAdapter(adapter7);
                    m4Spinner.setVisibility(View.INVISIBLE);

                    m5Spinner = (Spinner) findViewById(R.id.spinner8);
                    m5Spinner.setAdapter(adapter8);
                    m5Spinner.setVisibility(View.INVISIBLE);


                    //Listenerを登録
                    m1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName1 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName2 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName3 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

            // item.equals("5")
                } else {
                    m1Spinner = (Spinner) findViewById(R.id.spinner4);
                    m1Spinner.setAdapter(adapter4);
                    m1Spinner.setVisibility(View.VISIBLE);

                    m2Spinner = (Spinner) findViewById(R.id.spinner5);
                    m2Spinner.setAdapter(adapter5);
                    m2Spinner.setVisibility(View.VISIBLE);

                    m3Spinner = (Spinner) findViewById(R.id.spinner6);
                    m3Spinner.setAdapter(adapter6);
                    m3Spinner.setVisibility(View.VISIBLE);

                    m4Spinner = (Spinner) findViewById(R.id.spinner7);
                    m4Spinner.setAdapter(adapter7);
                    m4Spinner.setVisibility(View.VISIBLE);

                    m5Spinner = (Spinner) findViewById(R.id.spinner8);
                    m5Spinner.setAdapter(adapter8);
                    m5Spinner.setVisibility(View.VISIBLE);

                    //Listenerを登録
                    m1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName1 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName2 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName3 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m4Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName4 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m5Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner spinner = (Spinner) parent;
                            Cursor cursor = (Cursor) spinner.getSelectedItem();
                            mapName5 = cursor.getString(cursor.getColumnIndex("map_name"));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
                bestOf = String.valueOf(item3);
            }
        });

        Button button_next = (Button) findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, ResultActivity.class);
                intent.putExtra("TeamName1", textView1.getText());
//                Log.e("TeamName1", "data: " + textView1.getText());

                intent.putExtra("TeamName2", textView2.getText());
//                Log.e("TeamName2", "data: " + textView2.getText());

                intent.putExtra("BestOf", bestOf);
//                Log.e("BestOf", "data: " + bestOf);

                intent.putExtra("MapName1", mapName1);
//                Log.e("MapName1", "data: " + mapName1);

                intent.putExtra("MapName2", mapName2);
//                Log.e("MapName1", "data: " + mapName2);

                intent.putExtra("MapName3", mapName3);
//                Log.e("MapName1", "data: " + mapName3);

                intent.putExtra("MapName4", mapName4);
//                Log.e("MapName1", "data: " + mapName4);

                intent.putExtra("MapName5", mapName5);
//                Log.e("MapName1", "data: " + mapName5);

                startActivity(intent);
            }
        });

    }

}