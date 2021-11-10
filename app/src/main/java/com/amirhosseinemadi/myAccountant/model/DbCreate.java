package com.amirhosseinemadi.myAccountant.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCreate extends SQLiteOpenHelper {

    private static final String DB_NAME = "myAccountant";
    static final String TBL_NAME = "money";
    static final String TBL_NAME2 = "spend";
    static final String TBL_NAME3 = "chart";
    static final String TBL_NAME4 = "cheque";
    private static final int DB_VERSION = 3;

    public DbCreate(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table if not exists "+TBL_NAME+" (id integer primary key autoincrement , income long)";

        db.execSQL(query);

        String query2 = "create table if not exists "+TBL_NAME2+" (id integer primary key autoincrement , income long , rent long , health long" +
                ",food long,buy long , traffic long,clothes long,phone long,car long,hobby long , internet long,other long,time long,detial text,pay long)";

        db.execSQL(query2);

        String query3 = "create table if not exists "+TBL_NAME3+" (time long , income long)";

        db.execSQL(query3);

        String query4 = "create table if not exists "+TBL_NAME4+" (id integer primary key autoincrement , name text,value long,date long,alarm long,account text)";

        db.execSQL(query4);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
