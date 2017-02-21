package jp.ac.it_college.std.s14002.android.csgo_simulator;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {
    private Spinner lSpinner;
    private Spinner mSpinner;
    private Spinner nSpinner;

//    ListView listView = (ListView) findViewById(R.id.listview);

    private String spinnerItems0[] = {"TeamName1", "SK", "Virtus.pro", "dignitas", "Natus Vincere"};
    private String spinnerItems1[] = {"TeamName2", "SK", "Virtus.pro", "dignitas", "Natus Vincere"};
    private String spinnerItems2[] = {"Best of", "1", "2", "3", "5"}; //そのまま使う
    private String spinnerItems3[] = {"Map", "dust2", "inferno", "nuke", "train", "mirage", "cache", "cobblestone", "overpass"};
    private TextView textView1;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        DatabaseOpenHelper helper = new DatabaseOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        textView1 = (TextView) findViewById(R.id.text_team_name_1);
        textView2 = (TextView) findViewById(R.id.text_team_name_2);

        lSpinner = (Spinner) findViewById(R.id.spinner1);
        mSpinner = (Spinner) findViewById(R.id.spinner2);
        nSpinner = (Spinner) findViewById(R.id.spinner3);

        //SQL
        Cursor cursor = sqLiteDatabase.rawQuery("select team_id as _id, team_name from teams", null);

        cursor.moveToFirst();

        //adapterの準備
        //表示するカラム名
        String[] from = {"_id"};
        //バインドするViewリソース
        int[] to = {R.id.text1};

        //adapter生成
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.spinner_item , cursor,
                from, to, 0);

        //配列に格納

        //ドロップダウンリストのレイアウトを設定します。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //スピナーにadapterを設定します。
        Spinner spinner1 = (Spinner)this.findViewById(R.id.spinner1);
//        spinner1.setPrompt("以下のリストより選択して下さい。");
        //spinnerにadapter1をセットbindして表示
        spinner1.setAdapter(adapter);
        //クリックしたとき各行のデータを取得
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                Cursor cursor = (Cursor)spinner.getSelectedItem();
                int id1 = cursor.getInt(cursor.getColumnIndex("_id"));
                Toast.makeText(SelectActivity.this,
                        Integer.valueOf(id1).toString(),
                        Toast.LENGTH_LONG).show();
//                String s1 = ((TextView)view.findViewById(android.R.id.text1)).getText().toString();
//                String s2 = ((TextView)view.findViewById(android.R.id.text2)).getText().toString();

            }
            public void onNothingSelected(AdapterView parent) {
            }
        });

   /*     // ArrayAdapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerItems0);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinnerにadapter1をセット
        lSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerItems1);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinnerにadapter2をセット
        mSpinner.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerItems2);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinnerにadapter3をセット
        nSpinner.setAdapter(adapter3);


        //Listenerを登録 OK
        lSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                Spinner spinner = (Spinner) parent;
                String item1 = (String) spinner.getSelectedItem();

                if (item1.equals("TeamName1")) {
                    textView1.setText("TeamName1");
                } else if (item1.equals("SK")) {
                    textView1.setText("SK");
                } else if (item1.equals("Virtus.pro")) {
                    textView1.setText("Virtus.pro");
                } else if (item1.equals("dignitas")) {
                    textView1.setText("dignitas");
                } else {
                    textView1.setText("Natus Vincere ");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Listenerを登録 OK
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                Spinner spinner = (Spinner) parent;
                String item2 = (String) spinner.getSelectedItem();

                if (item2.equals("TeamName2")) {
                    textView2.setText("TeamName2");
                } else if (item2.equals("SK")) {
                    textView2.setText("SK");
                } else if (item2.equals("Virtus.pro")) {
                    textView2.setText("Virtus.pro");
                } else if (item2.equals("dignitas")) {
                    textView2.setText("dignitas");
                } else {
                    textView2.setText("Natus Vincere ");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Listenerを登録
        nSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                Spinner nSpinner = (Spinner) parent;
                final String item3 = (String) nSpinner.getSelectedItem();

                Spinner m1Spinner;
                Spinner m2Spinner;
                Spinner m3Spinner;
                Spinner m4Spinner;
                Spinner m5Spinner;


                // ArrayAdapter
                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(SelectActivity.this, R.layout.spinner_item, spinnerItems3);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // ArrayAdapter
                ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(SelectActivity.this, R.layout.spinner_item, spinnerItems3);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // ArrayAdapter
                ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(SelectActivity.this, R.layout.spinner_item, spinnerItems3);
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // ArrayAdapter
                ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(SelectActivity.this, R.layout.spinner_item, spinnerItems3);
                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // ArrayAdapter
                ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(SelectActivity.this, R.layout.spinner_item, spinnerItems3);
                adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                if (item3.equals("Best of")) {

                    m1Spinner = (Spinner) findViewById(R.id.spinner4);
                    m1Spinner.setAdapter(adapter4);
                    m1Spinner.setVisibility(View.INVISIBLE);

                    m2Spinner = (Spinner) findViewById(R.id.spinner5);
                    m2Spinner.setAdapter(adapter5);
                    m2Spinner.setVisibility(View.INVISIBLE);

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
                            Spinner m1Spinner = (Spinner) parent;
                            String item1 = (String) m1Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });


                } else if (item3.equals("1")) {

                    m1Spinner = (Spinner) findViewById(R.id.spinner4);
                    m1Spinner.setAdapter(adapter4);
                    m1Spinner.setVisibility(View.VISIBLE);

                    m2Spinner = (Spinner) findViewById(R.id.spinner5);
                    m2Spinner.setAdapter(adapter5);
                    m2Spinner.setVisibility(View.INVISIBLE);

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
                            Spinner m1Spinner = (Spinner) parent;
                            String item1 = (String) m1Spinner.getSelectedItem();
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
                            Spinner m1Spinner = (Spinner) parent;
                            String item2 = (String) m1Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner m2Spinner = (Spinner) parent;
                            String item3 = (String) m2Spinner.getSelectedItem();
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
                            Spinner m1Spinner = (Spinner) parent;
                            String item4 = (String) m1Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner m2Spinner = (Spinner) parent;
                            String item5 = (String) m2Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner m3Spinner = (Spinner) parent;
                            String item6 = (String) m3Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

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
                            Spinner m1Spinner = (Spinner) parent;
                            String item7 = (String) m1Spinner.getSelectedItem();
                            Log.d("Log", item7);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner m2Spinner = (Spinner) parent;
                            String item8 = (String) m2Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner m3Spinner = (Spinner) parent;
                            String item9 = (String) m3Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m4Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner m4Spinner = (Spinner) parent;
                            String item10 = (String) m4Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                    //Listenerを登録
                    m5Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                            Spinner m5Spinner = (Spinner) parent;
                            String item11 = (String) m5Spinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
*/


       /* //SQL
        Cursor cursor = sqLiteDatabase.rawQuery("select player_id as _id, player_name from players", null);

        //adapterの準備
        //表示するカラム名
        String[] from = {"_id", "player_name"};
        //バインドするViewリソース
        int[] to = {android.R.id.text1, android.R.id.text2};

        //adapter生成
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor,
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
*/


        Button button_next = (Button) findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, ResultActivity.class);
//                intent.putExtra("TeamName1", spinnerItems0[Integer.parseInt(Integer.toString(lSpinner.getSelectedItemPosition()))]);
//                intent.putExtra("TeamName2", spinnerItems1[Integer.parseInt(Integer.toString(mSpinner.getSelectedItemPosition()))]);
                startActivity(intent);
            }
        });

    }

}