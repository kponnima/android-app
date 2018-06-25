package com.example.kponnima.pharmacyassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kponnima on 9/25/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private static final  String DATABASE_NAME="QuestionsDatabase";

    public MyDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL("CREATE TABLE questions(_id INTEGER PRIMARY KEY AUTOINCREMENT, question TEXT, answernumber INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS questions");
        onCreate(db);
    }

    public void addQuestion(String question, int answernumber){
        ContentValues values = new ContentValues(2);
        values.put("question",question);
        values.put("answernumber",answernumber);
        getWritableDatabase().insert("questions","question",values);
        getWritableDatabase().close();
    }

    //Method to update the question
    public void editQuestion(int id, String question, int answernumber){
        ContentValues values = new ContentValues(3);
        values.put("_id",id);
        values.put("question",question);
        values.put("answernumber",answernumber);

        getWritableDatabase().update("questions",values,"_id="+id, null);
        getWritableDatabase().close();
    }

    //Method to delete the question
    public void deleteQuestion(int id, String question){
        getWritableDatabase().delete("questions","id=? and question=?",new String[]{"1","What now ?"});
        getWritableDatabase().close();
    }

    public Cursor getQuestions(){
        Cursor cursor = getReadableDatabase().query("questions",
                new String[] { "_id", "question", "answernumber"},
                null, null, null, null, null);
        return cursor;
    }

}
