package jp.ac.it_college.std.s14002.android.csgo_simulator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final DatabaseOpenHelper helper = new DatabaseOpenHelper(this);
        final SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        TextView teamName1 = (TextView) findViewById(R.id.text1);
        TextView teamName2 = (TextView) findViewById(R.id.text2);
        TextView Score1 = (TextView) findViewById(R.id.Score1);
        TextView Score2 = (TextView) findViewById(R.id.Score2);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("TeamName1");
//        Log.e("TeamName1", "data: " + name1);
        String name2 = intent.getStringExtra("TeamName2");
//        Log.e("TeamName2", "data: " + name2);
        String BestOf = intent.getStringExtra("BestOf");
//        Log.e("BestOf", "data: " + BestOf);
        String mapName1 = intent.getStringExtra("MapName1");
//        Log.e("MapName1", "data: " + mapName1);
        String mapName2 = intent.getStringExtra("MapName2");
//        Log.e("MapName1", "data: " + mapName2);
        String mapName3 = intent.getStringExtra("MapName3");
//        Log.e("MapName1", "data: " + mapName3);
        String mapName4 = intent.getStringExtra("MapName4");
//        Log.e("MapName1", "data: " + mapName4);
        String mapName5 = intent.getStringExtra("MapName5");
//        Log.e("MapName1", "data: " + mapName5);


        teamName1.setText(name1);
        teamName2.setText(name2);

        //SQL
        Cursor cursor1 = sqLiteDatabase.rawQuery("select team_id as _id from teams where team_name = '" + name1 + "'", null);

        cursor1.moveToFirst();
        int teamNameId1 = cursor1.getInt(cursor1.getColumnIndex("_id"));
        Log.e("Data", "a: " + teamNameId1);


        //SQL
        Cursor cursor2 = sqLiteDatabase.rawQuery("select team_id as _id from teams where team_name = '" + name2 + "'", null);

        cursor2.moveToFirst();
        int teamNameId2 = cursor2.getInt(cursor2.getColumnIndex("_id"));
        Log.e("Data", "b: " + teamNameId2);


        //SQL
        Cursor cursor3 = sqLiteDatabase.rawQuery("select player_rating from players where team_id= '" + teamNameId1 + "'", null);

        float rating1 = 0;
        cursor3.moveToFirst();
        for (int i = 0; i < 5; i++) {

            float rating_team_1 = cursor3.getFloat(cursor3.getColumnIndex("player_rating"));
            rating1 += rating_team_1;
            cursor3.moveToNext();
        }
        Log.e("a", "d :" + rating1);

        //SQL
        Cursor cursor4 = sqLiteDatabase.rawQuery("select player_rating from players where team_id= '" + teamNameId2 + "'", null);

        float rating2 = 0;
        cursor4.moveToFirst();
        for (int i = 0; i < 5; i++) {

            float rating_team_2 = cursor4.getFloat(cursor4.getColumnIndex("player_rating"));
            rating2 += rating_team_2;
            cursor4.moveToNext();
        }
        Log.e("b", "e :" + rating2);


        String mapName[] = {mapName1, mapName2, mapName3, mapName4, mapName5};
        int mapId[] = new int[5];
        if (BestOf.equals("1")) {
            Cursor cursor = sqLiteDatabase.rawQuery("select map_id as _id from map where map_name = '" + mapName[0] + "'", null);
            cursor.moveToFirst();
            int map_id1 = cursor.getInt(cursor.getColumnIndex("_id"));
            mapId[0] = map_id1;
            Log.e("BestOf", "a: " + map_id1);

        } else if (BestOf.equals("2")) {
            for (int i = 0; i < 2; i++) {
                Cursor cursor = sqLiteDatabase.rawQuery("select map_id as _id from map where map_name = '" + mapName[i] + "'", null);
                cursor.moveToFirst();
                int map_id1 = cursor.getInt(cursor.getColumnIndex("_id"));
                mapId[i] = map_id1;
                Log.e("BestOf", "a" + mapId[i]);
            }

        } else if (BestOf.equals("3")) {
            for (int i = 0; i < 3; i++) {
                Cursor cursor = sqLiteDatabase.rawQuery("select map_id as _id from map where map_name = '" + mapName[i] + "'", null);
                cursor.moveToFirst();
                int map_id1 = cursor.getInt(cursor.getColumnIndex("_id"));
                mapId[i] = map_id1;
                Log.e("BestOf", "a" + mapId[i]);
            }
        } else if (BestOf.equals("5")) {
            for (int i = 0; i < 5; i++) {
                Cursor cursor = sqLiteDatabase.rawQuery("select map_id as _id from map where map_name = '" + mapName[i] + "'", null);
                cursor.moveToFirst();
                int map_id1 = cursor.getInt(cursor.getColumnIndex("_id"));
                mapId[i] = map_id1;
                Log.e("BestOf", "a: " + mapId[i]);
            }
        }

        float all1 = 0;
        float all2 = 0;

        //SQL
        if (BestOf.equals("1")) {
            Cursor cursor5 = sqLiteDatabase.rawQuery("select win, lose from maps where team_id = '" + teamNameId1 + "' and map_id = '" + mapId[0] + "'", null);
            cursor5.moveToFirst();
            float team1_win = cursor5.getFloat(cursor5.getColumnIndex("win"));
//            Log.e("win", "a: " + team1_win);

            cursor5.moveToFirst();
            float team1_lose = cursor5.getFloat(cursor5.getColumnIndex("lose"));
//            Log.e("lose", "a: " + team1_lose);

            all1 = (rating1 * (team1_win / (team1_win + team1_lose)));
            Log.e("all1", "a" + all1);

            Cursor cursor = sqLiteDatabase.rawQuery("select win, lose from maps where team_id = '" + teamNameId2 + "' and map_id = '" + mapId[0] + "'", null);
            cursor.moveToFirst();
            float team2_win = cursor.getFloat(cursor.getColumnIndex("win"));
//            Log.e("win", "a: " + team2_win);

            cursor.moveToFirst();
            float team2_lose = cursor.getFloat(cursor.getColumnIndex("lose"));
//            Log.e("lose", "a: " + team2_lose);
            all2 = (rating2 * (team2_win / (team2_win + team2_lose)));
            Log.e("all2", "a" + all2);


        } else if (BestOf.equals("2")) {
            for (int i = 0; i < 2; i++) {
                Cursor cursor5 = sqLiteDatabase.rawQuery("select win, lose from maps where team_id = '" + teamNameId1 + "' and map_id = '" + mapId[i] + "'", null);
                cursor5.moveToFirst();
                float team1_win = cursor5.getFloat(cursor5.getColumnIndex("win"));
//            Log.e("win", "a: " + team1_win);

                cursor5.moveToFirst();
                float team1_lose = cursor5.getFloat(cursor5.getColumnIndex("lose"));
//            Log.e("lose", "a: " + team1_lose);

                all1 += (rating1 * (team1_win / (team1_win + team1_lose)));
                Log.e("all1", "a" + all1);

                Cursor cursor = sqLiteDatabase.rawQuery("select win, lose from maps where team_id = '" + teamNameId2 + "' and map_id = '" + mapId[i] + "'", null);
                cursor.moveToFirst();
                float team2_win = cursor.getFloat(cursor.getColumnIndex("win"));
//            Log.e("win", "a: " + team2_win);

                cursor.moveToFirst();
                float team2_lose = cursor.getFloat(cursor.getColumnIndex("lose"));
//            Log.e("lose", "a: " + team2_lose);
                all2 += (rating2 * (team2_win / (team2_win + team2_lose)));
                Log.e("all2", "a" + all2);
            }

        } else if (BestOf.equals("3")) {
            for (int i = 0; i < 3; i++) {
                Cursor cursor5 = sqLiteDatabase.rawQuery("select win, lose from maps where team_id = '" + teamNameId1 + "' and map_id = '" + mapId[i] + "'", null);
                cursor5.moveToFirst();
                float team1_win = cursor5.getFloat(cursor5.getColumnIndex("win"));
//            Log.e("win", "a: " + team1_win);

                cursor5.moveToFirst();
                float team1_lose = cursor5.getFloat(cursor5.getColumnIndex("lose"));
//            Log.e("lose", "a: " + team1_lose);

                all1 += (rating1 * (team1_win / (team1_win + team1_lose)));
                Log.e("all1", "a" + all1);

                Cursor cursor = sqLiteDatabase.rawQuery("select win, lose from maps where team_id = '" + teamNameId2 + "' and map_id = '" + mapId[i] + "'", null);
                cursor.moveToFirst();
                float team2_win = cursor.getFloat(cursor.getColumnIndex("win"));
//            Log.e("win", "a: " + team2_win);

                cursor.moveToFirst();
                float team2_lose = cursor.getFloat(cursor.getColumnIndex("lose"));
//            Log.e("lose", "a: " + team2_lose);
                all2 += (rating2 * (team2_win / (team2_win + team2_lose)));
                Log.e("all2", "a" + all2);
            }

        } else if (BestOf.equals("5")) {
            for (int i = 0; i < 5; i++) {
                Cursor cursor5 = sqLiteDatabase.rawQuery("select win, lose from maps where team_id = '" + teamNameId1 + "' and map_id = '" + mapId[i] + "'", null);
                cursor5.moveToFirst();
                float team1_win = cursor5.getFloat(cursor5.getColumnIndex("win"));
//            Log.e("win", "a: " + team1_win);

                cursor5.moveToFirst();
                float team1_lose = cursor5.getFloat(cursor5.getColumnIndex("lose"));
//            Log.e("lose", "a: " + team1_lose);

                all1 += (rating1 * (team1_win / (team1_win + team1_lose)));
                Log.e("all1", "a" + all1);

                Cursor cursor = sqLiteDatabase.rawQuery("select win, lose from maps where team_id = '" + teamNameId2 + "' and map_id = '" + mapId[i] + "'", null);
                cursor.moveToFirst();
                float team2_win = cursor.getFloat(cursor.getColumnIndex("win"));
//            Log.e("win", "a: " + team2_win);

                cursor.moveToFirst();
                float team2_lose = cursor.getFloat(cursor.getColumnIndex("lose"));
//            Log.e("lose", "a: " + team2_lose);
                all2 += (rating2 * (team2_win / (team2_win + team2_lose)));
                Log.e("all2", "a" + all2);
            }
        }
        int S = (int) (all1 / (all1 + all2) * 100);
        String ScA = String.valueOf(S);

        int L = (int) (all2 / (all1 + all2) * 100);
        String ScB = String.valueOf(L);
        Score1.setText(ScA + "%");
        Score2.setText(ScB + "%");

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
