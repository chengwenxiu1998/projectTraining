package com.cwx.imhuanxin.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.cwx.imhuanxin.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

//会话详情页面
public class ChatActivity extends FragmentActivity {
    private String mHxid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initData();
    }

    private void initData() {
        //创建一个会话的fragment
        EaseChatFragment easeChatFragment = new EaseChatFragment();
        mHxid = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        easeChatFragment.setArguments(getIntent().getExtras());
        Log.e("getIntent().getExtras()",getIntent().getExtras().toString());
        //替换fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_chat,easeChatFragment).commit();



    }


}
