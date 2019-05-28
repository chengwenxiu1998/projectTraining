package com.cwx.imhuanxin.model.dao;

//联系人表的建表语句
public class ContactTable {
    public static final  String TAB_NAME = "tab_cntact";

    public static final String COL_HXID = "hxid";
    public static final String COL_NAME = "name";
    public static final String COL_NICK = "nick";
    public static final String COL_PHOTO = "photo";

    public static final String COL_IS_CONTACT = "is_contact";//是否是好友，为啥有这个属性呢，如群组里，你俩不是好友，但是你能看到他的信息
    //他的信息也需要保存，如头像 名称，但他不是你的好友

    public static final String CREATE_TABLE = "create table "
            + TAB_NAME +" ("
            + COL_HXID + " text primary key,"
            + COL_NAME + " text,"
            + COL_NICK + " text,"
            + COL_PHOTO + " text,"
            + COL_IS_CONTACT + " integer);";//1是好友，0不是




}
