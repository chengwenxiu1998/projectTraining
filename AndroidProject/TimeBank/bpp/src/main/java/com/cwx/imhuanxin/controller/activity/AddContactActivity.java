package com.cwx.imhuanxin.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cwx.imhuanxin.R;
import com.cwx.imhuanxin.model.Model;
import com.cwx.imhuanxin.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

//添加联系人页面
public class AddContactActivity extends AppCompatActivity {
    private TextView tvAddFind;
    private EditText etAddName;
    private RelativeLayout rlAdd;
    private TextView tvAddName;
    private Button btnAddAdd;
    private UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        initView();

        initListener();
    }

    private void initListener() {
        //查找按钮的点击事件
        tvAddFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find();
            }
        });

        //添加按钮的点击事件
        btnAddAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }
    //查找按钮处理
    private void find() {
        //获取输入的用户名称
        final String name = etAddName.getText().toString();
        //校验输入的名称
        if(TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(),"输入的用户名称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //去服务器判断当前用户是否存在
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去服务器判断当前查找的用户是否存在
                userInfo = new UserInfo(name);
                //更新显示UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rlAdd.setVisibility(View.VISIBLE);
                        tvAddName.setText(userInfo.getName());
                    }
                });
            }
        });

    }

    //添加按钮处理
    private void add() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去环信服务器添加好友
                try {
                    EMClient.getInstance().contactManager().addContact(userInfo.getName(),"添加好友");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"发送添加好友邀请成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"发送添加好友邀请失败" + e,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        tvAddFind = findViewById(R.id.tv_add_find);
        etAddName = findViewById(R.id.et_add_name);
        rlAdd = findViewById(R.id.rl_add);
        tvAddName = findViewById(R.id.tv_add_name);
        btnAddAdd = findViewById(R.id.btn_add_add);
    }
}
