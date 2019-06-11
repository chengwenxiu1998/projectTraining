package com.cwx.timebank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cwx.imhuanxin.controller.activity.ChatActivity;
import com.cwx.timebank.task.ShowHeadImg;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private String hxid;
    private String nickname;

    @Override

protected void onCreate(@Nullable Bundle savedInstanceState){
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_detail);

        Button btnConnectionSeller = findViewById(R.id.btn_connectionseller);
        btnConnectionSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = DetailActivity.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                if (sp.getInt("userId", 0) != 0) {//若该用户已登录
                    Intent intent = new Intent(DetailActivity.this, ContactSellerActivity.class);
                    startActivity(intent);
                    jumpToTalkDetail();
                } else {//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
    
            }
        });
    
        //获取intent中的数据
        final Intent intent = getIntent();
        hxid = intent.getStringExtra("hxid");
        Log.e("DetailActivity", hxid);


        String uImage = intent.getStringExtra("uImage");
        nickname = intent.getStringExtra("nickname");



        String Time = intent.getStringExtra("Time");
        String taskTag = intent.getStringExtra("taskTag");
        String taskDetails = intent.getStringExtra("taskDetails");
        final int tId = intent.getIntExtra("tId", 0);
        TextView petname = findViewById(R.id.tv_detail_petname);
        TextView time = findViewById(R.id.tv_detail_time);
        TextView tag = findViewById(R.id.tv_tag);
        TextView detail = findViewById(R.id.tv_detail);
        CircleImageView touxaing = findViewById(R.id.iv_touxiang);

        if (uImage != null && !uImage.equals("")) {
            Log.e("uImage", uImage);
            SharedPreferences sp = DetailActivity.this.getSharedPreferences("myServer", MODE_PRIVATE);
            new ShowHeadImg(touxaing, sp.getString("serverUrl", "") + uImage).execute();
        }
    
        petname.setText(nickname);
        time.setText(Time);
        tag.setText(taskTag);
        detail.setText(taskDetails);
    
        Button btnGetTask = findViewById(R.id.gettask);
        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = DetailActivity.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                int uId = sharedPreferences.getInt("userId", 0);
                if (sharedPreferences.getInt("userId", 0) != 0) {//若该用户已登录
                    HandleDatabaseTask handleDatabaseTask = new HandleDatabaseTask(DetailActivity.this);
                    handleDatabaseTask.execute(tId, uId);
                    Toast.makeText(DetailActivity.this, "领取任务成功", Toast.LENGTH_LONG).show();
                } else {//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    //跳转到回话详情页面
    public void jumpToTalkDetail() {
    
        Intent intent1 = new Intent(getApplicationContext(), ChatActivity.class);
        intent1.putExtra(EaseConstant.EXTRA_USER_ID, hxid);
  intent1.putExtra(EaseConstant.EXTRA_USER_NICK,nickname);


startActivity(intent1);

        EMClient.getInstance().chatManager().addMessageListener(emMassageListener);
    }
    
    private EMMessageListener emMassageListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //设置数据
            //EaseUI.getInstance().getNotifier().onNewMesg(list);
            EaseUI.getInstance().getNotifier().notify(list);

//            for(int i=0;i<list.size();i++){
//                new Fragment().getArguments().putString(EaseConstant.EXTRA_USER_NICK,list.get(i).getStringAttribute("nickname",""));
//            }

            //刷新页面
            new com.hyphenate.easeui.ui.EaseConversationListFragment().refresh();
        }
    
        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {
    
        }
    
        @Override
        public void onMessageRead(List<EMMessage> list) {
    
        }
    
        @Override
        public void onMessageDelivered(List<EMMessage> list) {
    
        }
    
        @Override
        public void onMessageRecalled(List<EMMessage> list) {
    
        }
    
        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {
    
        }
    };
}
