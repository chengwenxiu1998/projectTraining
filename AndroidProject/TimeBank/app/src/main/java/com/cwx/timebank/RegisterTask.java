package com.cwx.timebank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.cwx.imhuanxin.model.Model;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

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

public class RegisterTask extends AsyncTask
{
    private Context context;
    private String petName;
    private String phone;
    private String password;

    public RegisterTask(Context context){
        this.context=context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        petName=(String)objects[0];
        phone=(String)objects[1];
        password=(String)objects[2];
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            URL url=new URL(serverUrl+"/RegisterActivity?petName="+petName+
                    "&phone="+phone+"&password="+password);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            JSONObject object=new JSONObject(res);
            User user=new User();
            user.setPetName(object.getString("petName"));
            user.setPhone(object.getString("phone"));
            user.setPassword(object.getString("password"));
            Log.e("xinxi",user.toString());
            Intent intent=new Intent();
            intent.setClass(context,LoginActivity.class);
            context.startActivity(intent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //注册的业务逻辑处理
    private void regist() {

        //3.去服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //去环信服务器注册账号
                    EMClient.getInstance().createAccount(phone,password);

                   Log.e("12","在环信服务器注册成功");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e("12", "在环信服务器注册成功");
                }
            }
        });

    }

}
