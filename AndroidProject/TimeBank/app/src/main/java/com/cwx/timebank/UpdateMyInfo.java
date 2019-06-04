package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwx.timebank.task.ShowHeadImg;

public class UpdateMyInfo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_my_info_layout);

        //点击返回箭头，返回到上一个页面
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMyInfo.this.finish();
            }
        });

       initView();

        //点击手机号，跳转到修改绑定的手机号界面
        LinearLayout llUpdatePhone = findViewById(R.id.ll_update_phone);
        llUpdatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),UpdatePhoneActivity.class);
                startActivity(intent);
            }
        });

        //点击地区，修改自己所在的地区
        LinearLayout llUpdateArea = findViewById(R.id.ll_update_area);
        llUpdateArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),UpdateAreaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initView(){
        //设置要显示的头像
        CircleImageView civHeadImg = findViewById(R.id.civ_head_img);
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String imgUrl = sp.getString("uImage","");
        if(imgUrl!=null && !imgUrl.equals("")){
            new ShowHeadImg(civHeadImg,imgUrl).execute();
        }

        //设置要显示的昵称
        TextView tvNickName = findViewById(R.id.tv_nickname);
        tvNickName.setText(sp.getString("uNickname",""));

        //设置要显示的ID
        TextView tvMyId = findViewById(R.id.tv_myid);
        String userId = sp.getInt("userId",0)+"";
        userId = String.format("%8s", userId).replaceAll(" ", "0");
        tvMyId.setText(userId);

        //设置显示的手机号
        TextView tvPhone = findViewById(R.id.tv_phone);
        tvPhone.setText(sp.getString("uPhone",""));

        //设置要显示的真实姓名
        TextView tvRealName = findViewById(R.id.tv_real_name);
        tvRealName.setText(sp.getString("uName",""));

        //设置要显示的性别
        TextView tvSex = findViewById(R.id.tv_sex);
        String sex = (sp.getInt("uSex",0)==0) ? "男" :"女";
        tvSex.setText(sex);

        //设置要显示的地区
        TextView tvArea = findViewById(R.id.tv_area);
        tvArea.setText(sp.getString("uArea",""));
    }
}
