package com.cwx.timebank.releasetask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.cwx.timebank.bean.TaskBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InsertTaskAsynTask extends AsyncTask<String,Void,Boolean> {
    String month;
    String day;
    String hour;
    String min;
    private TaskBean taskBean;
    private Context context;
    private Boolean aBoolean;
    private OnResponseListener<Boolean> listener;
    public InsertTaskAsynTask(Context context, TaskBean taskBean, String month, String day, String hour, String min) {
        this.context = context;
        this.taskBean = taskBean;
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.min=min;
    }
    public void setListener(OnResponseListener<Boolean> listener) {
        this.listener = listener;
    }
    @Override
    protected Boolean doInBackground(String... strings) {
        //通过网络访问服务器端登录功能
        URL url = null;
        String urlStr = "http://10.7.88.251:8080/TimeBank/insertTaskIntoDB?uIdSend="+taskBean.getuIdSend()+"&tcId="+taskBean.getTcId()+
                "&tDesc="+taskBean.gettDesc()+"&tCoinCount="+taskBean.gettCoinCount()+"&tState="+taskBean.gettState()
                +"&uIdAccept="+taskBean.getuIdAccept()+"&tagId="+taskBean.getTagId()+"&tEndtimeMonth="+month+"&tEndtimeDay="+day
                +"&tEndtimeHour="+hour+"&tEndtimeMin="+min
                +"&t_imgurl="+taskBean.getT_imgurl();
        try {
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("contentType","UTF-8");//如果给服务器端传的字符有中文，防止字符乱码问题
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);//转换流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            Log.e("cakeRes",res);
            /*//解析一个JSON格式的字符串
            Gson gson = new Gson();
            cakes= gson.fromJson(res, new TypeToken<List<Cake>>(){}.getType());*/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aBoolean;
    }
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (listener!=null){
            listener.onResponse(aBoolean);
        }
    }
    //接口 类似一个监听事件
    public interface OnResponseListener<T>{
        void onResponse(T t);
    }
}
