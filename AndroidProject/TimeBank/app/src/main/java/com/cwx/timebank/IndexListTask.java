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

        public IndexListTask(Context mContext,ListView lv){
            this.mContext=mContext;
            this.lv=lv;
        }
        @Override
        protected List doInBackground(String... strings) {
            List<NotAccept> tasksList = new ArrayList();
            try {
                //网络访问服务器端

<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> b600b5bafd91c761bcfc48085d8b2befc576b817
>>>>>>> c471a25f450c66dd36f43ca29e9346b72688709e
//            SharedPreferences sharedPreferences = mContext.getSharedPreferences("myServer", MODE_PRIVATE);
//            String serverUrl = sharedPreferences.getString("serverUrl","");
//            String urlStr = serverUrl+"/notaccepttask";
//            URL url = new URL(urlStr);
                URL url = new URL("http://10.7.88.211:8080/TimeBank/notaccepttask");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
<<<<<<< HEAD
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("myServer", MODE_PRIVATE);
                String serverUrl = sharedPreferences.getString("serverUrl","");
                String urlStr = serverUrl+"/notaccepttask";

=======

            SharedPreferences sharedPreferences = mContext.getSharedPreferences("myServer", MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("serverUrl","");
            String urlStr = serverUrl+"/notaccepttask";
<<<<<<< HEAD
          /*  URL url = new URL(urlStr);*/
//                URL url = new URL("http://10.7.88.211:8080/TimeBank/notaccepttask");
             /*   HttpURLConnection connection=(HttpURLConnection)url.openConnection();*/

=======
            URL url = new URL(urlStr);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();

>>>>>>> 0f5f2aec323868599269e4aacfa22e097e40742e
>>>>>>> b600b5bafd91c761bcfc48085d8b2befc576b817
>>>>>>> c471a25f450c66dd36f43ca29e9346b72688709e
                //传入的参数中有中文字符，防止乱码出现
                connection.setRequestProperty("contentType", "utf-8");
                //获取输入流
                InputStream in = connection.getInputStream();
                //字节流转换为字符流
                InputStreamReader inputStreamReader = new InputStreamReader(in);//转换流
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                Log.e("res", res);
                gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                tasksList = gson.fromJson(res, new TypeToken<List<NotAccept>>() {
                }.getType());
                if(tasksList!=null){
                    Log.e("NotAcceptTasksList", tasksList.toString());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return tasksList;
        }
<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======

>>>>>>> 0f5f2aec323868599269e4aacfa22e097e40742e
>>>>>>> b600b5bafd91c761bcfc48085d8b2befc576b817
>>>>>>> c471a25f450c66dd36f43ca29e9346b72688709e
        @Override
        protected void onPostExecute(List list) {
            if(list!=null &&list.size()!=0){
                IndexCustomAdapter2 adapter=new IndexCustomAdapter2(mContext,list,lv);
                lv.setAdapter(adapter);

            }else{
                Toast.makeText(mContext,"数据加载失败", Toast.LENGTH_SHORT).show();
            }
        }

<<<<<<< HEAD
=======
=======
<<<<<<< HEAD

=======
>>>>>>> 0f5f2aec323868599269e4aacfa22e097e40742e
>>>>>>> b600b5bafd91c761bcfc48085d8b2befc576b817
>>>>>>> c471a25f450c66dd36f43ca29e9346b72688709e
    }
