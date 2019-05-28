package com.cwx.imhuanxin.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.cwx.imhuanxin.R;
import com.cwx.imhuanxin.controller.fragment.ChatFragment;
import com.cwx.imhuanxin.controller.fragment.ContactListFragment;
import com.cwx.imhuanxin.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup rgMain;
    private ChatFragment chatFragment;
    private ContactListFragment contactListFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    //RadioGroup的选择事件
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId){
                    case R.id.rb_main_chat://会话列表页面
                        fragment = chatFragment;
                        break;
                    case R.id.rb_main_contact://联系人列表页面
                        fragment = contactListFragment;
                        break;
                    case R.id.rb_main_setting://设置页面
                        fragment = settingFragment;
                        break;

                }

                //实现Fragment切换的方法
                switchFragment(fragment);
            }
        });

        //默认选择会话列表页面
        rgMain.check(R.id.rb_main_chat);
    }

    //实现Fragment切换的方法
    private void switchFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();

    }

    private void initData() {
        //创建三个Fragment对象
        chatFragment = new ChatFragment();
        contactListFragment = new ContactListFragment();
        settingFragment = new SettingFragment();
    }

    private void initView() {
        rgMain = findViewById(R.id.rg_main);
    }


}
