package com.cwx.timebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SellTimeListTask extends AsyncTask<String,Void,List<SellTime>> {
    private Context mContext;
    private ListView lv;
    private Gson gson;

    public  SellTimeListTask(Context mContext,ListView lv){
        this.mContext=mContext;
        this.lv=lv;
    }
    @Override
    protected List doInBackground(String... strings){
        List<SellTime> tasksList=new ArrayList();
        try {
            //通过网络访问服务器端实现获取SellTime列表
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            URL url=new URL(serverUrl+"/selltimetask");
//            URL url = new URL("http://10.7.88.211:8080/TimeBank/selltimetask");
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            //传入的参数中有中文字符，防止乱码出现
            connection.setRequestProperty("contentType","utf-8");
            //获取输入流
            InputStream in=connection.getInputStream();
            //字节流转换为字符流
            InputStreamReader inputStreamReader=new InputStreamReader(in);//转换流
            BufferedReader reader=new BufferedReader(inputStreamReader);
            String res=reader.readLine();
            Log.e("res",res);
            gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            tasksList = gson.fromJson(res,new TypeToken<List<SellTime>>(){}.getType());
            Log.e("SellTimeTasksList",tasksList.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasksList;
    }

    @Override
    protected void onPostExecute(List list) {
        if(list!=null &&list.size()!=0){
            Log.e("12","嘿嘿嘿");
            SellTimeCustomAdapter adapter=new SellTimeCustomAdapter(mContext,list, R.layout.sell_time_item,lv);
            lv.setAdapter(adapter);
        }else{
            Toast.makeText(mContext,"数据加载失败", Toast.LENGTH_SHORT).show();
        }
    }
}
