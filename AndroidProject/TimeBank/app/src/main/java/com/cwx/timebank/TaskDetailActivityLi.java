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
import android.widget.ImageView;
import android.widget.TextView;

import com.cwx.imhuanxin.controller.activity.ChatActivity;
import com.cwx.timebank.task.ShowHeadImg;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import java.util.List;

public class TaskDetailActivityLi extends AppCompatActivity {
    private String hxid;
    private String nickname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetaili);



        ImageView imgReturn = findViewById(R.id.iv_return);
        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final Intent intent = getIntent();
        String uImage = intent.getStringExtra("uImage");
        nickname = intent.getStringExtra("nickname");
        String putTime = intent.getStringExtra("tPutTime");
        String taskTag = intent.getStringExtra("taskTag");
        String taskDetails = intent.getStringExtra("taskDetails");
        String tEndtime = intent.getStringExtra("tEndtime");
        String tImageUrl = intent.getStringExtra("tImageUrl");
        String tCoinCount = intent.getStringExtra("tCoinCount");
        hxid = intent.getStringExtra("hxid");
//        Log.e("TaskDetailActivityLi",hxid);
        CircleImageView touxiang = findViewById(R.id.iv_touxiang);
        TextView petname = findViewById(R.id.tv_detail_petname);
        TextView time = findViewById(R.id.tv_put_time);
        TextView tag = findViewById(R.id.tv_tag);
        TextView detail = findViewById(R.id.tv_detail);
        TextView money = findViewById(R.id.tv_money);
        TextView endTime = findViewById(R.id.tv_end_time);
        ImageView imageView = findViewById(R.id.iv_put_image);

        if (uImage != null && !uImage.equals("")) {
            SharedPreferences sp = TaskDetailActivityLi.this.getSharedPreferences("myServer", MODE_PRIVATE);
            new ShowHeadImg(touxiang, sp.getString("serverUrl", "") + uImage).execute();
        }
        if (tImageUrl != null && !tImageUrl.equals("")) {
            SharedPreferences sp = TaskDetailActivityLi.this.getSharedPreferences("myServer", MODE_PRIVATE);
            new ShowHeadImg(imageView, sp.getString("serverUrl", "") + tImageUrl).execute();
        }

        petname.setText(nickname);
        time.setText(putTime);
        tag.setText(taskTag);
        detail.setText(taskDetails);
        money.setText(tCoinCount);
        endTime.setText(tEndtime);

        Button btnConnectionSeller = findViewById(R.id.btn_connectionseller);
        btnConnectionSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = TaskDetailActivityLi.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                if (sharedPreferences.getInt("userId", 0) != 0) {//若该用户已登录
                    jumpToTalkDetail();
                } else {//用户还没有登陆，跳转到登陆页面
                    Intent intent = new Intent(TaskDetailActivityLi.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
        //跳转到回话详情页面
        public void jumpToTalkDetail(){

            Intent intent1 = new Intent(getApplicationContext(), ChatActivity.class);
            intent1.putExtra(EaseConstant.EXTRA_USER_ID,hxid);
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
