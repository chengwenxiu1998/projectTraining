package com.cwx.timebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static android.content.Context.MODE_PRIVATE;
public class IndexListTask extends AsyncTask<String,Void,List<NotAccept>>{
        private Context mContext=null;
        private ListView lv=null;
        private Gson gson;

<<<<<<< HEAD
        public IndexListTask(Context mContext,ListView lv){
            this.mContext=mContext;
            this.lv=lv;
        }
        @Override
        protected List doInBackground(String... strings) {
            List<NotAccept> tasksList=new ArrayList();
            try {
                //网络访问服务器端
//            SharedPreferences sharedPreferences = mContext.getSharedPreferences("myServer", MODE_PRIVATE);
//            String serverUrl = sharedPreferences.getString("serverUrl","");
//            String urlStr = serverUrl+"/notaccepttask";
//            URL url = new URL(urlStr);
                URL url = new URL("http://10.7.88.211:8080/TimeBank/notaccepttask");
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
                tasksList = gson.fromJson(res,new TypeToken<List<NotAccept>>(){}.getType());
                Log.e("NotAcceptTasksList",tasksList.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
=======
public class IndexListTask extends AsyncTask<String,Void,List>{
>>>>>>> 0d26325e73bf14aaa426e564b209a82589479b6c
    private Context mContext=null;
    private ListView lv=null;
    private Gson gson;

    public IndexListTask(Context mContext,ListView lv){
        this.mContext=mContext;
        this.lv=lv;
    }
    @Override
    protected List doInBackground(String... strings) {
        List<NotAccept> tasksList=new ArrayList();
        try {
            //网络访问服务器端
<<<<<<< HEAD
            URL url = new URL("http://10.7.88.211:8080/TimeBank/notaccepttask");
=======
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/IndexServlet";
            URL url = new URL(urlStr);
>>>>>>> 0d26325e73bf14aaa426e564b209a82589479b6c
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            //传入的参数中有中文字符，防止乱码出现
            connection.setRequestProperty("contentType","utf-8");
            //获取输入流
            InputStream in=connection.getInputStream();
            //字节流转换为字符流
            InputStreamReader inputStreamReader=new InputStreamReader(in);//转换流
            BufferedReader reader=new BufferedReader(inputStreamReader);
            String res=reader.readLine();
<<<<<<< HEAD
            Log.e("res",res);
            gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            tasksList = gson.fromJson(res,new TypeToken<List<NotAccept>>(){}.getType());
            Log.e("NotAcceptTasksList",tasksList.toString());
=======
            //解析JSONArray字符串
            JSONArray array=new JSONArray(res);
            for(int i=0;i<array.length();++i){
                JSONObject object=array.optJSONObject(i);
                BuyOrSellTime buyOrSellTime=new BuyOrSellTime();

                buyOrSellTime.setuNickName(object.optString("uNickName"));
                //sellTime.setuImage(object.optString("uImage"));
                buyOrSellTime.setTagText(object.optString("tagText"));

                //获取到JSON中的时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dstr=object.optString("uTime");
                String dstr2=object.optString("tEndtime");
                Date date= null;
                Date date2= null;
                try {
                    date = sdf.parse(dstr);
                    date2=sdf.parse(dstr2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                buyOrSellTime.setuTime(date);
                buyOrSellTime.settEndtime(date2);

                buyOrSellTime.settDesc(object.optString("tDesc"));
                buyOrSellTime.settCoinCount(object.getInt("tCoinCount"));
                buyOrSellTime.settId(object.getInt("tId"));
                buyOrSellTime.setuIdAccept(object.getInt("uIdAccept"));
                buyOrSellTime.setTagText(object.optString("tagText"));
                tasksList.add(buyOrSellTime);
>>>>>>> beddea0c78462ebf91fc6e587c99e5ae98160e62
            }
            return tasksList;
        }

        @Override
        protected void onPostExecute(List list) {
            if(list!=null &&list.size()!=0){
                IndexCustomAdapter2 adapter=new IndexCustomAdapter2(mContext,list,lv);
                lv.setAdapter(adapter);

            }else{
                Toast.makeText(mContext,"数据加载失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
