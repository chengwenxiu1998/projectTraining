package com.cwx.timebank.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.cwx.imhuanxin.controller.activity.LoginActivity;
import com.cwx.imhuanxin.model.Model;
import com.cwx.imhuanxin.model.bean.UserInfo;
import com.cwx.timebank.MainActivity;
import com.cwx.timebank.bean.User;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

public class LoginTask extends AsyncTask<String,Object,User>{
    private TextView tvError;
    private Context context;
    private CheckBox cbRememberPwd;

    private String phoneNum;
    private String password;


    public LoginTask(TextView tvError,Context context,CheckBox cbRememberPwd){
        this.context = context;
        this.tvError = tvError;
        this.cbRememberPwd = cbRememberPwd;
    }

    @Override
    protected User doInBackground(String[] strings) {
        User user = null;
        phoneNum = strings[0];
        password = strings[1];
        Boolean isLoginSuccessful = false;
        //通过网络访问服务器端登录功能
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/user/login?phoneNum="+phoneNum+"&password="+password;
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();

            Gson gson = new Gson();
           user = gson.fromJson(res,User.class);

         } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    protected void onPostExecute(User user) {
        if(user!=null){//登录成功
            SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isRememberPwd",cbRememberPwd.isChecked());//保存该用户是否勾选记住密码，true表示记住密码，false表示不记住密码
            String upassword = "";
            int uid = 0;

                upassword = user.getuPassword();
                uid = user.getuId();
                editor.putInt("userId",uid);
                editor.putString("uName", user.getuName());
                editor.putString("uPhone",user.getuPhone());
                editor.putInt("uSex",user.getuSex());
                editor.putString("uArea",user.getuArea());
                editor.putString("uNickname",user.getuNickName());
                SharedPreferences sp = context.getSharedPreferences("myServer", MODE_PRIVATE);
                editor.putString("uImage",sp.getString("serverUrl","")+user.getuImage());
                editor.putString("uPassword",upassword);
                editor.putString("uIdCard",user.getuIdCard());
                editor.putInt("uCoin",user.getuCoin());

//                editor.putInt("signDayCount",(Integer)signInInfo.get("signDayCount"));
////                editor.putInt("ifSignIn",(Integer)signInInfo.get("ifSignIn"));
////                editor.putInt("finishCount",(Integer)signInInfo.get("finishCount"));


            editor.commit();

            SharedPreferences sp2 = context.getSharedPreferences("isFromMy",MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sp2.edit();
            editor1.putBoolean("fromMy",true);
            editor1.commit();
            Intent intent = new Intent(context,MainActivity.class);
            context.startActivity(intent);

            //本地服务器登陆成功后，去环信服务器登录
            login(uid+"",upassword);

        }else{//登录失败
            tvError.setText("登录失败");
        }
    }

    //登录按钮的页面逻辑处理
    public void login(final String uid, final String pwd) {

        //登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去环信服务器登录
                Log.e("login",uid+":::" + pwd);
                EMClient.getInstance().login(uid, pwd, new EMCallBack() {
                    //登录成功后的处理
                    @Override
                    public void onSuccess() {
                        //对模型层数据的处理
                        Model.getInstance().loginSuccess(new UserInfo(uid));
                        //保存用户账号信息到本地DB
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(uid));
                        //提示登录成功
                        Log.e("12","环信服务器登录成功");

                    }
                    //登录失败后的处理
                    @Override
                    public void onError(int i, final String s) {
                        //提示登录失败
                        Log.e("12","环信服务器登录失败");
                    }
                    //登录过程中的处理
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }
}
