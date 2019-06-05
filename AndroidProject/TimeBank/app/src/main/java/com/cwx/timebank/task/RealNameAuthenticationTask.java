package com.cwx.timebank.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cwx.timebank.SetActivity;
import com.google.gson.Gson;

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

public class RealNameAuthenticationTask extends AsyncTask<Object,Object,Boolean> {
    private Context context;
    private int uid;
    private String name;
    private String shenFenZheng;

    public RealNameAuthenticationTask(Context context,int uid,String name,String shenFenZheng){
        this.context = context;
        this.uid = uid;
        this.name = name;
        this.shenFenZheng = shenFenZheng;
    }

    @Override
    protected Boolean doInBackground(Object[] objects) {
        boolean isAuthenticationSuccessful = false;
        //将真实姓名和身份证号存入数据库，并修改SharedPreferences中存的值
        URL url = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/user/realNameAuthentication?uid="+uid+"&name="+name+"&idCard="+shenFenZheng;
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();



                int updateRowCount =  new Gson().fromJson(res,int.class);
                if(updateRowCount != 0){
                    isAuthenticationSuccessful = true;
                }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return isAuthenticationSuccessful;
    }

    @Override
    protected void onPostExecute(Boolean isAuthenticationSuccessful) {
        super.onPostExecute(isAuthenticationSuccessful);
        if(isAuthenticationSuccessful){//如果认证成功的话，跳转到设置界面,在SharedPreferences，保存认证信息，用户名和身份证号
            SharedPreferences sp = context.getSharedPreferences("userInfo",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("uName",name);
            editor.putString("uIdCard",shenFenZheng);
            editor.remove("uSex");
            //根据身份证号的倒数第二位判断性别
            String substring = shenFenZheng.substring(16, 17);
            System.out.println("substring" + substring);
            byte sex = (byte)((Integer.parseInt(substring)%2==0)? 1 : 0);//女1，男0
            editor.putInt("uSex",sex);
            editor.commit();
            Intent intent=new Intent();
            intent.setClass(context,SetActivity.class);
            context.startActivity(intent);
        }else{//认证失败，请重新认证
            Toast.makeText(context,"系统抖了一下，请您稍后再来认证",Toast.LENGTH_LONG).show();
        }
    }
}