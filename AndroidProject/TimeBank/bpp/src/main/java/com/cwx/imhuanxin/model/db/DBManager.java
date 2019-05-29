package com.cwx.imhuanxin.model.db;

import android.content.Context;

import com.cwx.imhuanxin.model.dao.ContactTableDao;
import com.cwx.imhuanxin.model.dao.InviteTableDao;

//负责邀请信息和联系人信息这2张表的处理
//联系人和邀请信息表的操作类的管理类
public class DBManager {
    private final DBHelper dbHelper;
    private ContactTableDao contactTableDao;
    private InviteTableDao inviteTableDao;

    public  DBManager(Context context, String name){
        //创建DB
        dbHelper = new DBHelper(context,name);
        //创建该数据库中2张表的操作类
        contactTableDao = new ContactTableDao(dbHelper);
        inviteTableDao = new InviteTableDao(dbHelper);



    }
    //获取联系人表的操作类对象
    public ContactTableDao getContactTableDao() {
        return contactTableDao;
    }
    //获取邀请信息表的操作类对象

    public InviteTableDao getInviteTableDao() {
        return inviteTableDao;
    }

    //关闭数据库的方法
    public void close() {
        dbHelper.close();
    }
}
