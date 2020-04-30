package com.android.lib_assistant.common.SqlHelper;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.lib_assistant.common.HelperStuffs.Message;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


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
//    public String getEmployeeName(String typeData) {
//        Cursor cursor = null;
//        String empName = "";
//        try {
//            SQLiteDatabase db = myhelper.getWritableDatabase();
//            if(typeData.equals("name")){
//                cursor = db.rawQuery("SELECT "+ myDbHelper.ANSWER +" FROM "+ myDbHelper.TABLE_NAME, null);
//            }else if(typeData.equals("email")){
//                cursor = db.rawQuery("SELECT "+ myDbHelper.MyEmail+" FROM "+ myDbHelper.TABLE_NAME, null);
//            }else if(typeData.equals("image")){
//                cursor = db.rawQuery("SELECT "+ myDbHelper.IMAGE+" FROM "+ myDbHelper.TABLE_NAME, null);
//            }else if(typeData.equals("phone")){
//                cursor = db.rawQuery("SELECT "+ myDbHelper.PHONE+" FROM "+ myDbHelper.TABLE_NAME, null);
//            }else if (typeData.equals("gender")){
//                cursor = db.rawQuery("SELECT "+ myDbHelper.GENDER+" FROM "+ myDbHelper.TABLE_NAME, null);
//            }else if (typeData.equals("address")){
//                cursor = db.rawQuery("SELECT "+ myDbHelper.ADDRESS+" FROM "+ myDbHelper.TABLE_NAME, null);
//            }else if (typeData.equals("user_id")){
//                cursor = db.rawQuery("SELECT "+ myDbHelper.USER_ID+" FROM "+ myDbHelper.TABLE_NAME, null);
//            }else if (typeData.equals("password")){
//                cursor = db.rawQuery("SELECT "+ myDbHelper.MyPASSWORD+" FROM "+ myDbHelper.TABLE_NAME, null);
//            }
//          //  cursor = db.rawQuery("SELECT "+myDbHelper.NAME+" FROM "+myDbHelper.TABLE_NAME, null);
//            if(cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                if(typeData.equals("name")){
//                    empName = cursor.getString(cursor.getColumnIndex(myDbHelper.ANSWER));
//                }else if(typeData.equals("email")){
//                    empName = cursor.getString(cursor.getColumnIndex(myDbHelper.MyEmail));
//                }else if(typeData.equals("image")){
//                    empName = cursor.getString(cursor.getColumnIndex(myDbHelper.IMAGE));
//                }else if(typeData.equals("phone")){
//                    empName = cursor.getString(cursor.getColumnIndex(myDbHelper.PHONE));
//                }else if (typeData.equals("gender")){
//                    empName = cursor.getString(cursor.getColumnIndex(myDbHelper.GENDER));
//                }else if (typeData.equals("address")){
//                    empName = cursor.getString(cursor.getColumnIndex(myDbHelper.ADDRESS));
//                }else if (typeData.equals("user_id")){
//                    empName = cursor.getString(cursor.getColumnIndex(myDbHelper.USER_ID));
//                }else if (typeData.equals("password")){
//                    empName = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
//                }
//
//            }
//            return empName;
//        }finally {
//            cursor.close();
//        }
//    }
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

    public int updateName(String id , String question, String answer)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.ID_ROW,id);
        contentValues.put(myDbHelper.QUESTION,question);
        contentValues.put(myDbHelper.ANSWER,answer);



        String[] whereArgs= {id};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.ID_ROW+" = ?",whereArgs );
        return count;
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
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}

