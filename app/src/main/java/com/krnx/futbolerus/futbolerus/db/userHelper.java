package com.krnx.futbolerus.futbolerus.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.krnx.futbolerus.futbolerus.R;

/**
 * Created by arnau on 27/07/16.
 */
public class userHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "futbolerus";
    public static final String TABLE_NAME = "user";
    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            "email VARCHAR(50) PRIMARY KEY UNIQUE, " +
            "name VARCHAR(15) , " +
            "surname VARCHAR(15), " +
            "stats VARCHAR(100)" +
            "photo VARCHAR(100), " +
            "id VARCHAR(100) UNIQUE, " +
            "token VARCHAR(100)" +
            ");";
    private static final String TAG = "Futbolerus - SQL";

    public userHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    public Boolean insertUser(Context context, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();

        if (db != null) {
            try {
                db.insertOrThrow(
                        TABLE_NAME,
                        null,
                        values);
                db.close();
                return true;
            } catch (SQLiteConstraintException e){
                Log.v(TAG, e.getMessage());
                Toast.makeText(context, R.string.user_already_registered, Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return false;
    }

    public void insertUserTest(String query){
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            try {
                db.execSQL(query);
//                db.execSQL("Insert into user values ('arnau.pratc@gmail.com','Arnau','Prat','01/02/1984','sdfsd sdfds')");
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            db.close();
        }
    }

}
