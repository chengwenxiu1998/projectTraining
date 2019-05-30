package com.cwx.imhuanxin;

import android.app.Application;
import android.content.Context;

import com.cwx.imhuanxin.model.Model;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

public class IMApplication extends Application{
    private static  Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化EaseUI
        EMOptions emOptions = new EMOptions();
        emOptions.setAcceptInvitationAlways(false);//设置需要同意后才能接受邀请
        emOptions.setAutoAcceptGroupInvitation(false);//设置需要同意后才能接群邀请
        EaseUI.getInstance().init(this,emOptions);

        //初始化数据模型层类
        Model.getInstance().init(this);

        //初始化全局上下文
        mContext = this;
    }

    //获取全局上下文对象
    public static Context getGlobalApplication(){
        return mContext;
    }
}
