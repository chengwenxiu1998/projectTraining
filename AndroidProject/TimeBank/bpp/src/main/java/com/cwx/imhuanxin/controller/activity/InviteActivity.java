package com.cwx.imhuanxin.controller.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.cwx.imhuanxin.R;
import com.cwx.imhuanxin.controller.adapter.InviteAdapter;
import com.cwx.imhuanxin.model.Model;
import com.cwx.imhuanxin.model.bean.InvationInfo;
import com.cwx.imhuanxin.utils.Constant;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

public class InviteActivity extends Activity{
    private LocalBroadcastManager mLBM;
    private ListView lvInvite;
    private InviteAdapter inviteAdapter;
    private InviteAdapter.OnInviteListener mOnInviteListener = new InviteAdapter.OnInviteListener() {
        @Override
        public void onAccept(final InvationInfo invationInfo) {
            //通知环信服务器 点击了接收按钮
            Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().contactManager().acceptInvitation(invationInfo.getUser().getHxid());
                        //DB更新
                        Model.getInstance().getDbManager().getInviteTableDao().updateInvitationStatus(InvationInfo.InvitationStatus.INVITE_ACCCEPT,invationInfo.getUser().getHxid());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //页面发生变化
                                Toast.makeText(getApplicationContext(),"接受了邀请",Toast.LENGTH_SHORT).show();
                                //刷新页面
                                refresh();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //页面发生变化
                                Toast.makeText(getApplicationContext(),"接受邀请失败",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                }
            });

        }

        @Override
        public void onReject(final InvationInfo invationInfo) {
            Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().contactManager().declineInvitation(invationInfo.getUser().getHxid());
                        //DB变化
                        Model.getInstance().getDbManager().getInviteTableDao().removeInvitation(invationInfo.getUser().getHxid());

                        //页面变化
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"拒绝成功了",Toast.LENGTH_SHORT).show();
                                //刷新页面
                                refresh();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"拒绝失败了",Toast.LENGTH_SHORT).show();
                                //刷新页面
                                refresh();
                            }
                        });
                    }
                }
            });

        }
    };
    private BroadcastReceiver ContactInviteChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //刷新页面
            refresh();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        initView();
        initData();
    }

    private void initData() {
        //初始化listView
        inviteAdapter = new InviteAdapter(this,mOnInviteListener);
        lvInvite.setAdapter(inviteAdapter);

        //刷新方法
        refresh();
        //注册邀请信息变化的广播
        mLBM = LocalBroadcastManager.getInstance(this);
        mLBM.registerReceiver(ContactInviteChangedReceiver,new IntentFilter(Constant.CONTACT_INVITE_CHANGED));
    }

    private void refresh() {
        //获取DB中的所有邀请信息
        List<InvationInfo> invitations = Model.getInstance().getDbManager().getInviteTableDao().getInvitations();
        Log.e("12","几个信息" + invitations.size());
        Log.e("12",invitations.toString());
       //刷新适配器
        inviteAdapter.refresh(invitations);
    }

    private void initView() {
        lvInvite = findViewById(R.id.lv_invite);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLBM.unregisterReceiver(ContactInviteChangedReceiver);
    }
}
