package com.cwx.timebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

<<<<<<< HEAD
import com.cwx.timebank.bean.TaskBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
=======
import com.cwx.timebank.task.BuyTime;
import com.google.gson.Gson;
>>>>>>> 4e9bcc94b87b0cd9fd120d7b17221bd18421e005
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

public class BuyTimeListTask extends AsyncTask<String,Void,List<BuyTime>> {
    private Context mContext=null;
    private ListView lv=null;
    private List<BuyTime> tasksList;
    private Gson gson;

    public BuyTimeListTask(Context mContext, ListView lv){
        this.mContext=mContext;
        this.lv=lv;
    }
    @Override
<<<<<<< HEAD
    protected List<BuyTime> doInBackground(String... strings){
        tasksList=new ArrayList<BuyTime>();
        try {
            //通过网络访问服务器端实现获取BuTime列表
//            SharedPreferences sharedPreferences = mContext.getSharedPreferences("myServer", MODE_PRIVATE);
//            String serverUrl = sharedPreferences.getString("serverUrl","");
//            URL url=new URL(serverUrl+"/buytimetask");
            URL url = new URL("http://10.7.88.211:8080/TimeBank/buytimetask");
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
            gson = new Gson();
            tasksList = gson.fromJson(res,new TypeToken<List<BuyTime>>(){}.getType());
            Log.e("TasksList",tasksList.toString());
=======
    protected List doInBackground(String... strings){
        List<BuyTime> tasksList=new ArrayList();
        //通过网络访问服务器端登录功能
        URL url = null;
        String urlStr = "http://10.7.88.251:8080/TimeBank/task";
        try {
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            Log.e("cakeRes",res);
>>>>>>> 4e9bcc94b87b0cd9fd120d7b17221bd18421e005

            //解析一个JSON格式的字符串
            Gson gson = new Gson();
            tasksList= gson.fromJson(res, new TypeToken<List<BuyTime>>(){}.getType());
            Log.e("taskList",tasksList.toString());
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
            BuyTimeCustomAdapter adapter=new BuyTimeCustomAdapter(mContext,list, R.layout.buy_time_item,lv);
            lv.setAdapter(adapter);
        }else{
            Toast.makeText(mContext,"数据加载失败", Toast.LENGTH_SHORT).show();
        }
    }
}
