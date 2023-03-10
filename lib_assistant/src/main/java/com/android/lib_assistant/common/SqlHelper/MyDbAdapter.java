package com.android.lib_assistant.common.SqlHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.lib_assistant.common.HelperStuffs.UiUtilities;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;
import java.util.ArrayList;
import java.util.List;
public class MyDbAdapter {
    myDbHelper myhelper;
    public MyDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(List<PatternQuestionAnswer> patternQuestionAnswer)
    {
        long idOfInsert=0;
        List<PatternQuestionAnswer> pattern = patternQuestionAnswer;
        for(int i =0 ;i<patternQuestionAnswer.size();i++){
            if(patternQuestionAnswer.get(i).getQuestion().contains("who made you")||
                    patternQuestionAnswer.get(i).getQuestion().contains("What's your name")||
                    patternQuestionAnswer.get(i).getQuestion().contains("made you")||
                    patternQuestionAnswer.get(i).getQuestion().contains("your name")||
                    patternQuestionAnswer.get(i).getQuestion().contains("who own you")||
                    patternQuestionAnswer.get(i).getQuestion().contains("when is your birthday")||
                    patternQuestionAnswer.get(i).getQuestion().contains("your birthday")
            ){
            }else{
                SQLiteDatabase dbb = myhelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(myDbHelper.ID_ROW, pattern.get(i).getIndex());
                contentValues.put(myDbHelper.QUESTION,pattern.get(i).getQuestion());
                contentValues.put(myDbHelper.ANSWER, pattern.get(i).getAnswer());
                contentValues.put(myDbHelper.ACTION_KEY, pattern.get(i).getActionKey());
                idOfInsert = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
            }
        }

        return idOfInsert;
    }
    public List<PatternQuestionAnswer> getData()
    {
        List<PatternQuestionAnswer> patternQuestionAnswersList = new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.ANSWER, myDbHelper.QUESTION, myDbHelper.ID_ROW,myDbHelper.ACTION_KEY};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String answer =cursor.getString(cursor.getColumnIndex(myDbHelper.ANSWER));
            String question =cursor.getString(cursor.getColumnIndex(myDbHelper.QUESTION));
            String id =cursor.getString(cursor.getColumnIndex(myDbHelper.ID_ROW));
            String actionKey = cursor.getString(cursor.getColumnIndex(myDbHelper.ACTION_KEY));
            patternQuestionAnswersList.add(new PatternQuestionAnswer(Integer.parseInt(id),question,answer,Integer.parseInt(actionKey)));
        }
        return patternQuestionAnswersList;
    }
    public  void delete()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("delete from "+ myDbHelper.TABLE_NAME);
    }
    public static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String ID_ROW ="id_row" ;
        private static final String ACTION_KEY ="action_key" ;
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String QUESTION = "Name";    //Column II
        private static final String ANSWER = "answer";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ ID_ROW +" Integer ,"+ QUESTION+" VARCHAR(255),"+ANSWER+" VARCHAR(255),"+ ACTION_KEY +" Integer);";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                UiUtilities.showToast(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                UiUtilities.showToast(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                UiUtilities.showToast(context,""+e);
            }
        }
    }
}

