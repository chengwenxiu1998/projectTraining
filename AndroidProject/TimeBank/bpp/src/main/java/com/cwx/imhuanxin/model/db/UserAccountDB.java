package com.cwx.imhuanxin.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cwx.imhuanxin.model.dao.UserAccountTable;

public class UserAccountDB extends SQLiteOpenHelper {
    public UserAccountDB(Context context) {
        super(context, "account.db", null, 1);
    }

    //数据库创建的时候调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库表的语句
        db.execSQL(UserAccountTable.CTEATE_TAB);

    }

    //数据库更新的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
