package com.cwx.imhuanxin.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cwx.imhuanxin.model.bean.GroupInfo;
import com.cwx.imhuanxin.model.bean.InvationInfo;
import com.cwx.imhuanxin.model.bean.UserInfo;
import com.cwx.imhuanxin.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class InviteTableDao {
    private DBHelper mHelper;
    public InviteTableDao(DBHelper helper) {
        this.mHelper = helper;
    }

    //添加邀请
    public void addInvitation(InvationInfo invationInfo){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(InviteTable.COL_REASON,invationInfo.getReason());//原因
        values.put(InviteTable.COL_STATUS,invationInfo.getStatus().ordinal());//状态
        Log.e("添加邀请中的状态,",invationInfo.getStatus().ordinal()+int2InviteStatus(invationInfo.getStatus().ordinal()).name());

        UserInfo user = invationInfo.getUser();
        if(user != null){//联系人
            values.put(InviteTable.COL_USER_HXID,invationInfo.getUser().getHxid());
            values.put(InviteTable.COL_USER_NAME,invationInfo.getUser().getName());
        }else {//群组
            values.put(InviteTable.COL_GROUP_HXID,invationInfo.getGroup().getGroupId());
            values.put(InviteTable.COL_GROUP_NAME,invationInfo.getGroup().getGroupName());
            values.put(InviteTable.COL_USER_HXID,invationInfo.getGroup().getInvatePerson());
        }
        Log.e("存入数据库时的values",values.toString());
        db.replace(InviteTable.TAB_NAME,null,values);
    }

    //获取所有邀请信息
    public List<InvationInfo> getInvitations(){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select * from " + InviteTable.TAB_NAME;
        Cursor cursor = db.rawQuery(sql,null);

        List<InvationInfo> invationInfos = new ArrayList<>();
        while (cursor.moveToNext()){
            InvationInfo invationInfo = new InvationInfo();

            invationInfo.setReason(cursor.getString(cursor.getColumnIndex(InviteTable.COL_REASON)));//原因
            Log.e("从数据库中取出的状态的int类型",cursor.getInt(cursor.getColumnIndex(InviteTable.COL_STATUS))+"");
            invationInfo.setStatus(int2InviteStatus(cursor.getInt(cursor.getColumnIndex(InviteTable.COL_STATUS))));

            String groupId = cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_HXID));
            if(groupId == null){//联系人的邀请信息
                UserInfo userInfo = new UserInfo();
                userInfo.setHxid(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_HXID)));
                userInfo.setName(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_NAME)));
                userInfo.setNick(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_NAME)));
                invationInfo.setUser(userInfo);
            }else {//群组的邀请信息
                GroupInfo groupInfo = new GroupInfo();
                groupInfo.setGroupId(cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_HXID)));
                groupInfo.setGroupName(cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_NAME)));
                groupInfo.setInvatePerson(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_HXID)));
                invationInfo.setGroup(groupInfo);
            }
            //添加本次循环的邀请信息到总的集合中
            invationInfos.add(invationInfo);

            Log.e("获取所有邀请信息",invationInfos.toString());




        }
        cursor.close();
        return invationInfos;

    }

    //将int类型状态转换为邀请的状态
    private InvationInfo.InvitationStatus int2InviteStatus(int intStatus){
        if(intStatus == InvationInfo.InvitationStatus.NEW_INVITE.ordinal()){
            return InvationInfo.InvitationStatus.NEW_INVITE;
        }
        if(intStatus == InvationInfo.InvitationStatus.INVITE_ACCCEPT.ordinal()){
            return InvationInfo.InvitationStatus.INVITE_ACCCEPT;
        }
        if(intStatus == InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER.ordinal()){
            return InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER;
        }


        if(intStatus == InvationInfo.InvitationStatus.NEW_GROUP_INVITE.ordinal()){
            return InvationInfo.InvitationStatus.NEW_GROUP_INVITE;
        }

        if(intStatus == InvationInfo.InvitationStatus.NEW_GROUP_APPLICATION.ordinal()){
            return InvationInfo.InvitationStatus.NEW_GROUP_APPLICATION;
        }

        if(intStatus == InvationInfo.InvitationStatus.GROUP_INVITE_ACCEPTED.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_INVITE_ACCEPTED;
        }

        if(intStatus == InvationInfo.InvitationStatus.GROUP_APPLICATION.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_APPLICATION;
        }

        if(intStatus == InvationInfo.InvitationStatus.GROUP_ACCEPT_INVITE.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_ACCEPT_INVITE;
        }

        if(intStatus == InvationInfo.InvitationStatus.GRUP_ACCEPT_APPLICATION.ordinal()){
            return InvationInfo.InvitationStatus.GRUP_ACCEPT_APPLICATION;
        }

        if(intStatus == InvationInfo.InvitationStatus.GROUP_REJECT_INVITE.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_REJECT_INVITE;
        }

        if(intStatus == InvationInfo.InvitationStatus.GROUP_REJECT_APPLICATION.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_REJECT_APPLICATION;
        }

        if(intStatus == InvationInfo.InvitationStatus.GROUP_INVITE_DECLINED.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_INVITE_DECLINED;
        }

        if(intStatus == InvationInfo.InvitationStatus.GROUP_APPLICATION_DECLINED.ordinal()){
            return InvationInfo.InvitationStatus.GROUP_APPLICATION_DECLINED;
        }


        return null;

    }

    //删除邀请信息
    public void removeInvitation(String hxId){
        if(hxId == null){
            return;
        }

        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.delete(InviteTable.TAB_NAME,InviteTable.COL_USER_HXID + "=?",new String[]{hxId});
    }

    //更新邀请状态
    public void updateInvitationStatus(InvationInfo.InvitationStatus invitationStatus ,String hxId){
        if(hxId==null){
            return;
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(InviteTable.COL_STATUS,invitationStatus.ordinal());

        db.update(InviteTable.TAB_NAME,values,InviteTable.COL_USER_HXID+"=?",new String[]{hxId});

    }


}
