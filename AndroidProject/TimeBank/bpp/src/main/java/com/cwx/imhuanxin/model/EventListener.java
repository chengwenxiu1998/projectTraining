package com.cwx.imhuanxin.model;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.cwx.imhuanxin.model.bean.InvationInfo;
import com.cwx.imhuanxin.model.bean.UserInfo;
import com.cwx.imhuanxin.utils.Constant;
import com.cwx.imhuanxin.utils.SpUtils;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

//全局事件监听类
public class EventListener  {
    private Context mContext;
    private LocalBroadcastManager mLBM;


    public EventListener(Context context){
        mContext = context;
        //创建一个发送广播的管理者对象
        mLBM = LocalBroadcastManager.getInstance(mContext);

        //注册一个联系人变化的监听
        EMClient.getInstance().contactManager().setContactListener(emContactListener);
    }

    private final EMContactListener emContactListener = new EMContactListener() {
       //联系人增加后执行的方法
        @Override
        public void onContactAdded(String hxId) {
            //数据库更新
            Model.getInstance().getDbManager().getContactTableDao().saveContact(new UserInfo(hxId),true);
            //发送联系人变化的广播
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_CHANGED));

        }

        //联系人删除后执行的方法
        @Override
        public void onContactDeleted(String hxId) {
    //数据库更新
            Model.getInstance().getDbManager().getContactTableDao().deleteContactByHxId(hxId);
            Model.getInstance().getDbManager().getInviteTableDao().removeInvitation(hxId);
            //发送联系人变化的广播
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_CHANGED));
        }

        //接收到联系人的新邀请
        @Override
        public void onContactInvited(String hxid, String reason) {
            //数据库更新
            InvationInfo invationInfo = new InvationInfo();
            invationInfo.setUser(new UserInfo(hxid));
            invationInfo.setReason(reason);
            invationInfo.setStatus(InvationInfo.InvitationStatus.NEW_INVITE);//新邀请
            Model.getInstance().getDbManager().getInviteTableDao().addInvitation(invationInfo);

            //红点的处理
            SpUtils.getInstance().save(SpUtils.IS_NEW_INVITE,true);

            //发送邀请信息变化的广播
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGED));


        }

        //别人同意了你的好友邀请
        @Override
        public void onFriendRequestAccepted(String hxid) {
            //数据库更新
            InvationInfo invationInfo = new InvationInfo();
            invationInfo.setUser(new UserInfo(hxid));
            invationInfo.setStatus(InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER);//别人同意了你的邀请
            Model.getInstance().getDbManager().getInviteTableDao().addInvitation(invationInfo);

            //红点的处理
            SpUtils.getInstance().save(SpUtils.IS_NEW_INVITE,true);

            //发送邀请信息变化的广播
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGED));
        }

        //别人拒绝了你的好友邀请
        @Override
        public void onFriendRequestDeclined(String s) {
            //红点的处理
            SpUtils.getInstance().save(SpUtils.IS_NEW_INVITE,true);
            //发送邀请信息变化的广播
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGED));
        }
    };
}
