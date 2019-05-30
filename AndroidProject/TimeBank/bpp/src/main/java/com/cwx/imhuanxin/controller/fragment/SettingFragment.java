package com.cwx.imhuanxin.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cwx.imhuanxin.R;
import com.cwx.imhuanxin.controller.activity.LoginActivity;
import com.cwx.imhuanxin.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

//设置页面
public class SettingFragment extends Fragment {
    private Button btnSettingOut;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = View.inflate(getActivity(), R.layout.fragment_setting,null);
       initView(view);

       return view;
    }

    private void initView(View view) {
        btnSettingOut = view.findViewById(R.id.btn_setting_out);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {
        //在Button上显示当前用户名称
        btnSettingOut.setText("退出登录（" + EMClient.getInstance().getCurrentUser() + "）");
        //退出登录的逻辑处理
        btnSettingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        //登录环信服务器退出登录
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                //关闭DBHelper
                                Model.getInstance().getDbManager().close();

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //更新UI显示
                                        Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
                                        //回答登录页面
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);

                                        getActivity().finish();

                                    }
                                });



                            }

                            @Override
                            public void onError(int i, final String s) {

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(getActivity(),"退出失败" + s,Toast.LENGTH_SHORT).show();


                                    }
                                });

                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });

                    }
                });
            }
        });
    }
}
