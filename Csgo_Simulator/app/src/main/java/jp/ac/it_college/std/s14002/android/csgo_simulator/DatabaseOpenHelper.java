package jp.ac.it_college.std.s14002.android.csgo_simulator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by s14002 on 17/02/17.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final long serialVersionID = 1L;
    private static final String DB_FILE_NAME = "matome.db";
    private static final String DB_NAME = "database.db"; //androidが使うDBファイル名
    private static final int DB_VERSION = 2;
    private Context context;
    private File dbPath;
    private boolean databaseExist = true; //適切なDBファイルが存在するか



    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        this.dbPath = context.getDatabasePath(DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onOpen(db);
        databaseExist = false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //Database更新時の処理
        String databasePath = this.dbPath.getAbsolutePath();
        File file = new File(databasePath);
        if (file.exists()) {
            file.delete();
        }
        databaseExist = false;
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase database = super.getWritableDatabase();
        if (!databaseExist) {
            try {
                database.close();
                database = copyDatabaseFromAssets();
                databaseExist = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return database;
    }

    /**
     * assetsにあるデータベースをdata/data/package/databasesにコピーする
     *
     * @throws IOException
     */
    private SQLiteDatabase copyDatabaseFromAssets() throws IOException {
        InputStream inputstream = this.context.getAssets().open(DB_FILE_NAME);
        OutputStream outputStream = new FileOutputStream(dbPath);

        byte[] buffer = new byte[1024];
        int size;
        while ((size = inputstream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, size);
        }
        outputStream.flush();
        outputStream.close();
        inputstream.close();

        return super.getWritableDatabase();
    }


}
