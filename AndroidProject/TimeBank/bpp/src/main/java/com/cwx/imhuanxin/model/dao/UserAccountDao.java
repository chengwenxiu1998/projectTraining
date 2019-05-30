package com.cwx.imhuanxin.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cwx.imhuanxin.model.bean.UserInfo;
import com.cwx.imhuanxin.model.db.UserAccountDB;

//用户账号数据库的操作类
public class UserAccountDao {
    private  final UserAccountDB mHelper;
    public UserAccountDao(Context context){
        mHelper = new UserAccountDB(context);
    }

    //添加用户到数据库
    public void addAccount(UserInfo userInfo){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行添加操作[用户账号在数据库中有就不创建了，没有就添加；insert只添加]
        ContentValues values = new ContentValues();
        values.put(UserAccountTable.COL_HXID, userInfo.getHxid());
        values.put(UserAccountTable.COL_NAME, userInfo.getName());
        values.put(UserAccountTable.COL_NICK, userInfo.getNick());
        values.put(UserAccountTable.COL_PHOTO, userInfo.getPhoto());
        db.replace(UserAccountTable.TAB_NAME,null,values);

    }

    //根据环信id获取所有用户信息
    public UserInfo getAccountByHxId(String hxId){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = "select * from " + UserAccountTable.TAB_NAME + " where " + UserAccountTable.COL_HXID + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});
        UserInfo userInfo = null;
        if(cursor.moveToNext()){
            userInfo = new UserInfo();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));


        }
        //关闭资源

        cursor.close();

        //返回数据
        return userInfo;
    }
}
