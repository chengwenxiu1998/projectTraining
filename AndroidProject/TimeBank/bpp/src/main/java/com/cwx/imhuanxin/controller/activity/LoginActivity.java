package com.cwx.imhuanxin.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cwx.imhuanxin.R;
import com.cwx.imhuanxin.model.Model;
import com.cwx.imhuanxin.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPassword;
    private Button btnRegist;
    private Button btnLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化控件
        initView();

        //初始化监听
        initListener();
    }

    public void initListener() {
        //注册按钮的点击事件处理
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });

        //登录按钮的点击事件处理
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    //登录按钮的页面逻辑处理
    public void login() {
        //1.获取输入的用户名和密码
        final String name = etName.getText().toString();
        final String password =etPassword.getText().toString();
        //2.校验输入的用户名和密码
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //3.登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去环信服务器登录
                EMClient.getInstance().login(name, password, new EMCallBack() {
                    //登录成功后的处理
                    @Override
                    public void onSuccess() {
                        //对模型层数据的处理
                        Model.getInstance().loginSuccess(new UserInfo(name));
                        //保存用户账号信息到本地DB
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(name));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //提示登录成功
                                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                                //跳转到主页面
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);

                                finish();
                            }
                        });


                    }
                    //登录失败后的处理
                    @Override
                    public void onError(int i, final String s) {
                        //提示登录失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"登录失败" + s,Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    //登录过程中的处理
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }

    //注册的业务逻辑处理
    private void regist() {
        //1.获取输入的用户名和密码
        final String name = etName.getText().toString();
        final String password =etPassword.getText().toString();
        //2.校验输入的用户名和密码
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //3.去服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //去环信服务器注册账号
                    EMClient.getInstance().createAccount(name,password);

                    //更新页面显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        btnRegist = findViewById(R.id.btn_regist);
        btnLogin = findViewById(R.id.btn_finish);

    }
}
