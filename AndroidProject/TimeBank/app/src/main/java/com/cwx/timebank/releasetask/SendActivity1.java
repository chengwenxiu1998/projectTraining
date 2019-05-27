package com.cwx.timebank.releasetask;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.cwx.timebank.MainActivity;

import com.cwx.timebank.R;
import com.cwx.timebank.bean.TaskBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class SendActivity1 extends Fragment {
    TabHost tabHost;
    private TaskBean taskBean;
    float scaleWidth;
    float scaleHeight;
    int h;
    boolean num=false;
    Bitmap bp;
    ImageView imageview;
    private int i=0;//用来作为图片的名称

    public static final int TAKE_PHOTO1 = 3;
    public static final int TAKE_PHOTO = 2;//声明一个请求码，用于识别返回的结果
    private Uri imageUri;

    private File mPhotoFile;
    private String mPhotoPath;
    public final static int CAMERA_RESULT = 1;
    ImageView  imageshow;
    ImageView  imageshowSell;
    ImageView ivAddImageView;
    ImageView ivSellAddImageView;
    private List<View> viewList=new ArrayList<View>();
    private String[] tabHostTag={"tab1","tab2"};
    private final int ImageCode = 0;
    private final int ImageCode1 = 1;
    private final String Image_Type="image/*";

    EditText etSendTask;
    EditText etSellSendTask;
    // EditText etSellTimeMoney;
    //  EditText etSellSellTimeMoney;
    EditText etMiaoshu;
    EditText etSellMiaoshu;
    Spinner spinner;
    Spinner spinner1;
    Spinner spinnerEndTimeMonth;
    Spinner spinnerEndTimeDay;
    Spinner spinnerEndTimeHour;
    Spinner spinnerEndTimeMin;
    Spinner spinnerEndTimeMonth1;
    Spinner spinnerEndTimeDay1;
    Spinner spinnerEndTimeHour1;
    Spinner spinnerEndTimeMin1;
    String month;
    String day;
    String hour;
    String min;
    PopupWindow popupWindow;
    //当创建View时调用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_send,container,false);
        return view;
    }

    //当View创建完成
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //返回箭头 到首页
        ImageView ivReturn = view.findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[0]).setIndicator(getTabView("买时间")). setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec(tabHostTag[1]).setIndicator(getTabView("卖时间")) .setContent(R.id.tab2));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabHostChanged(tabId);
            }
        });

        setTabHostChanged(tabHost.getCurrentTabTag());


        etSendTask=view.findViewById(R.id.et_send_task);
        etMiaoshu=view.findViewById(R.id.et_miaoshu);


        etSellSendTask=view.findViewById(R.id.et_sell_send_task);
        etSellMiaoshu=view.findViewById(R.id.et_sell_miaoshu);

        final TextView tvSellTimeMoney=view.findViewById(R.id.tv_time_money);
        // etSellTimeMoney=view.findViewById(R.id.et_time_money);

        final TextView tvSellSellTimeMoney=view.findViewById(R.id.tv_sell_time_money);
        //etSellSellTimeMoney=view.findViewById(R.id.et_sell_time_money);


        //spinner
        spinner= view.findViewById(R.id.sp_spinner_shijianbi);
        spinner1= view.findViewById(R.id.sp_sell_spinner_shijianbi);
        spinnerEndTimeMonth= view.findViewById(R.id.sp_endtime_month);
        spinnerEndTimeMonth1= view.findViewById(R.id.sp_endtime_month1);
        spinnerEndTimeDay = view.findViewById(R.id.sp_endtime_day);
        spinnerEndTimeDay1 = view.findViewById(R.id.sp_endtime_day1);
        final String[] strDay1 = new String[31];
        for(int i=0;i<31;i++){strDay1[i]=i+1+"";}
        final String[] strDay2 = new String[30];
        for(int i=0;i<30;i++){strDay2[i]=i+1+"";}
        final String[] strDay3 = new String[29];
        for(int i=0;i<29;i++){strDay3[i]=i+1+"";}
        final String[] strDay4 = new String[28];
        for(int i=0;i<28;i++){strDay4[i]=i+1+"";}
        spinnerEndTimeMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp=spinnerEndTimeMonth.getSelectedItem().toString();
                if (temp.equals("1")||temp.equals("3")||temp.equals("5")||temp.equals("7")||temp.equals("8")||temp.equals("10")||temp.equals("12")){
                    ArrayAdapter daySpinnerAdapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay1);
                    spinnerEndTimeDay.setAdapter(daySpinnerAdapter1);
                }else if (temp.equals("4")||temp.equals("6")||temp.equals("9")||temp.equals("11")){
                    ArrayAdapter daySpinnerAdapter2 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay2);
                    spinnerEndTimeDay.setAdapter(daySpinnerAdapter2);
                }else if(temp.equals("2")){
                    Calendar c = Calendar.getInstance();//
                    int mYear = c.get(Calendar.YEAR); // 获取当前年份
                    if ((mYear%4)==0){
                        ArrayAdapter daySpinnerAdapter3 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay3);
                        spinnerEndTimeDay.setAdapter(daySpinnerAdapter3);
                    }else{
                        ArrayAdapter daySpinnerAdapter4 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay4);
                        spinnerEndTimeDay.setAdapter(daySpinnerAdapter4);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter daySpinnerAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay1);
                spinnerEndTimeDay.setAdapter(daySpinnerAdapter);
            }
        });
        spinnerEndTimeMonth1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp=spinnerEndTimeMonth1.getSelectedItem().toString();
                if (temp.equals("1")||temp.equals("3")||temp.equals("5")||temp.equals("7")||temp.equals("8")||temp.equals("10")||temp.equals("12")){
                    ArrayAdapter daySpinnerAdapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay1);
                    spinnerEndTimeDay1.setAdapter(daySpinnerAdapter1);
                }else if (temp.equals("4")||temp.equals("6")||temp.equals("9")||temp.equals("11")){
                    ArrayAdapter daySpinnerAdapter2 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay2);
                    spinnerEndTimeDay1.setAdapter(daySpinnerAdapter2);
                }else if(temp.equals("2")){
                    Calendar c = Calendar.getInstance();//
                    int mYear = c.get(Calendar.YEAR); // 获取当前年份
                    if ((mYear%4)==0){
                        ArrayAdapter daySpinnerAdapter3 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay3);
                        spinnerEndTimeDay1.setAdapter(daySpinnerAdapter3);
                    }else{
                        ArrayAdapter daySpinnerAdapter4 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay4);
                        spinnerEndTimeDay1.setAdapter(daySpinnerAdapter4);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter daySpinnerAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,strDay1);
                spinnerEndTimeDay.setAdapter(daySpinnerAdapter);
            }
        });

        spinnerEndTimeHour = view.findViewById(R.id.sp_endtime_hour);
        spinnerEndTimeHour1 = view.findViewById(R.id.sp_endtime_hour1);
        spinnerEndTimeMin = view.findViewById(R.id.sp_endtime_min);
        spinnerEndTimeMin1 = view.findViewById(R.id.sp_endtime_min1);


        final Button btnHelpBuyFood=view.findViewById(R.id.btn_help_buy_food);
        btnHelpBuyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnHelpBuyFood.getText());
                etSendTask.setCursorVisible(false);
                etMiaoshu.setHint("描述详情");
                // etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnSellHelpBuyFood=view.findViewById(R.id.btn_sell_help_buy_food);
        btnSellHelpBuyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellHelpBuyFood.getText());
                etSellSendTask.setCursorVisible(false);
                etSellMiaoshu.setHint("描述详情");
                //  etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnDaiqu=view.findViewById(R.id.btn_daiqukuaidi);
        btnDaiqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnDaiqu.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                //  etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellDaiqu=view.findViewById(R.id.btn_sell_daiqukuaidi);
        btnSellDaiqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellDaiqu.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                //  etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnDaijie=view.findViewById(R.id.btn_daijieshui);
        btnDaijie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnDaijie.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                //etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellDaijie=view.findViewById(R.id.btn_sell_daijieshui);
        btnSellDaijie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellDaijie.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                //   etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnYueguang=view.findViewById(R.id.btn_yueguangjie);
        btnYueguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnYueguang.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                // etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellYueguang=view.findViewById(R.id.btn_sell_yueguangjie);
        btnSellYueguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellYueguang.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                //etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnYueyun=view.findViewById(R.id.btn_yueyundong);
        btnYueyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnYueyun.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                //etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellYueyun=view.findViewById(R.id.btn_sell_yueyundong);
        btnSellYueyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellYueyun.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                //etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnYuexue=view.findViewById(R.id.btn_yuexuexi);
        btnYuexue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnYuexue.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                // etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellYuexue=view.findViewById(R.id.btn_sell_yuexuexi);
        btnSellYuexue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellYuexue.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");
                // etSellSellTimeMoney.setHint("请输入你得到的时间币");
                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        final Button btnQita=view.findViewById(R.id.btn_qita);
        btnQita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSendTask.setText(btnQita.getText());
                etSendTask.setCursorVisible(false);

                etMiaoshu.setHint("描述详情");
                // etSellTimeMoney.setHint("请输入你想花费的时间币");
                tvSellTimeMoney.setVisibility(View.VISIBLE);
            }
        });

        final Button btnSellQita=view.findViewById(R.id.btn_sell_qita);
        btnSellQita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSellSendTask.setText(btnSellQita.getText());
                etSellSendTask.setCursorVisible(false);

                etSellMiaoshu.setHint("描述详情");

                tvSellSellTimeMoney.setVisibility(View.VISIBLE);

            }
        });

        ivAddImageView=view.findViewById(R.id.iv_add_photo);
        ivAddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        imageshow = (ImageView)view.findViewById(R.id.iv_photo);

        ivSellAddImageView=view.findViewById(R.id.iv_sell_add_photo);
        ivSellAddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow1();
            }
        });
        imageshowSell = (ImageView)view.findViewById(R.id.iv_sell_photo);



        Button btnSend=view.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskBean=new TaskBean();

                //发布任务
                task(tabHost.getCurrentTabTag());
                //假设发布者ID为1
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);
                int userId = sharedPreferences.getInt("userId",0);
                Log.e("test",userId+"");
                taskBean.setuIdSend(userId);

<<<<<<< HEAD
                 InsertTaskAsynTask renwuTask=new InsertTaskAsynTask(getContext(),taskBean,month,day,hour,min);
=======
                InsertTaskAsynTask renwuTask=new InsertTaskAsynTask(getContext(),taskBean,month,day,hour,min);
               /* renwuTask.setListener(new InsertTaskAsynTask.OnResponseListener<Boolean>() {
                    @Override
                    public void onResponse(Boolean aBoolean) {
                        if (aBoolean==true){
                            Toast.makeText(getContext(),"发布成功",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(),"网络错误，发布失败",Toast.LENGTH_LONG).show();
                        }
                    }
                });*/
               renwuTask.setListener(new InsertTaskAsynTask.OnResponseListener<Boolean>() {
                   @Override
                   public void onResponse(Boolean aBoolean) {

                   }
               });
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of cd0c4a3... Revert "limeiqing"
=======
>>>>>>> parent of cd0c4a3... Revert "limeiqing"
=======
>>>>>>> parent of cd0c4a3... Revert "limeiqing"
                renwuTask.execute();
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                Log.e("test","点击了发表");
            }
        });

    }
    public void task(String tabId){
        if (tabId.equals(tabHostTag[0])) {
            taskBean.setTcId(1);
            taskBean.settDesc(etMiaoshu.getText().toString());
            taskBean.settCoinCount(Integer.parseInt(spinner.getSelectedItem().toString()));
            Log.e("test","spinner="+spinner.getSelectedItem().toString());
            //状态先写待接收
            taskBean.settState("待接收");
            //TagID
            if(etSendTask.getText().toString().equals("#代取快递#")) {
                taskBean.setTagId(1);
            }else if(etSendTask.getText().toString().equals("#代买饭#")){
                taskBean.setTagId(2);
            }else if(etSendTask.getText().toString().equals("#代接水#")){
                taskBean.setTagId(3);
            }else if(etSendTask.getText().toString().equals("#约逛街#")){
                taskBean.setTagId(4);
            }else if(etSendTask.getText().toString().equals("#约运动#")){
                taskBean.setTagId(5);
            }else if(etSendTask.getText().toString().equals("#约学习#")){
                taskBean.setTagId(6);
            }else if(etSendTask.getText().toString().equals("#其他#")){
                taskBean.setTagId(7);
            }else{
                Log.e("test","设置taskBean出错");
            }
            //结束时间
            month=spinnerEndTimeMonth.getSelectedItem().toString();
            day=spinnerEndTimeDay.getSelectedItem().toString();
            hour=spinnerEndTimeHour.getSelectedItem().toString();
            min=spinnerEndTimeMin.getSelectedItem().toString();
            Log.e("test",month+" "+day+" "+hour+"  "+min);


            //先假设一个路径
            taskBean.setT_imgurl("aaaaa.jpg");

        } else if (tabId.equals(tabHostTag[1])) {
            taskBean.setTcId(2);
            taskBean.settDesc(etSellMiaoshu.getText().toString());
            taskBean.settCoinCount(Integer.parseInt(spinner.getSelectedItem().toString()));
            Log.e("test","spinner1="+spinner1.getSelectedItem().toString());
            //状态先写待接收
            taskBean.settState("待接收");
            //TagID
            if(etSendTask.getText().toString().equals("#代取快递#")) {
                taskBean.setTagId(1);
            }else if(etSendTask.getText().toString().equals("#代买饭#")){
                taskBean.setTagId(2);
            }else if(etSendTask.getText().toString().equals("#代接水#")){
                taskBean.setTagId(3);
            }else if(etSendTask.getText().toString().equals("#约逛街#")){
                taskBean.setTagId(4);
            }else if(etSendTask.getText().toString().equals("#约运动#")){
                taskBean.setTagId(5);
            }else if(etSendTask.getText().toString().equals("#约学习#")){
                taskBean.setTagId(6);
            }else if(etSendTask.getText().toString().equals("#其他#")){
                taskBean.setTagId(7);
            }else{
                Log.e("test","设置taskBean出错");
            }
            //结束时间
            month=spinnerEndTimeMonth1.getSelectedItem().toString();
            day=spinnerEndTimeDay1.getSelectedItem().toString();
            hour=spinnerEndTimeHour1.getSelectedItem().toString();
            min=spinnerEndTimeMin1.getSelectedItem().toString();
            Log.e("test",month+" "+day+" "+hour+"  "+min);
            //先假设一个路径
            taskBean.setT_imgurl("aaaaa.jpg");

        }
    }


    // 弹出PopupWindow
    private void showPopupWindow1() {
        // 1. 创建PopupWindow显示的view
        final View view = getLayoutInflater()
                .inflate(R.layout.popup_window, null);
        // 2. 创建PopupWindow
        popupWindow = new PopupWindow(getContext());
        // 3. 设置PopupWindow的长宽
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 4. 设置PopupWindow显示的view
        popupWindow.setContentView(view);
        //点击空白处popupWindow消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 5. view中按钮添加监听器
        Button btnPhoto = view.findViewById(R.id.btn_photo);
        Button btnSelect = view.findViewById(R.id.btn_select);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        MyListener myListener=new MyListener();
        btnCancel.setOnClickListener(myListener);
        btnPhoto.setOnClickListener(myListener);
        btnSelect.setOnClickListener(myListener);

        popupWindow.showAtLocation(ivSellAddImageView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    // 弹出PopupWindow
    private void showPopupWindow() {
        // 1. 创建PopupWindow显示的view
        final View view = getLayoutInflater()
                .inflate(R.layout.popup_window, null);
        // 2. 创建PopupWindow
        popupWindow= new PopupWindow(getContext());
        // 3. 设置PopupWindow的长宽
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 4. 设置PopupWindow显示的view
        popupWindow.setContentView(view);
        //点击空白处popupWindow消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 5. view中按钮添加监听器
        Button btnPhoto = view.findViewById(R.id.btn_photo);
        Button btnSelect = view.findViewById(R.id.btn_select);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        MyListener myListener=new MyListener();
        btnCancel.setOnClickListener(myListener);
        btnPhoto.setOnClickListener(myListener);
        btnSelect.setOnClickListener(myListener);

        popupWindow.showAtLocation(ivAddImageView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);

    }

<<<<<<< HEAD
<<<<<<< HEAD



=======



>>>>>>> parent of cd0c4a3... Revert "limeiqing"
=======



>>>>>>> parent of cd0c4a3... Revert "limeiqing"
    private void setTabHostChanged(String tabId) {
        TextView textView1 = viewList.get(0).findViewById(R.id.tv_text);
        TextView textView2 = viewList.get(1).findViewById(R.id.tv_text);
        TextView tvLine1=viewList.get(0).findViewById(R.id.tv_line);
        TextView tvLine2=viewList.get(1).findViewById(R.id.tv_line);
        textView1.setText("买时间");
        textView2.setText("卖时间");
        if (tabId.equals(tabHostTag[0])) {
            textView1.setText(Html.fromHtml("<font color=#ffcc66>买时间</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#ffcc66"));
            tvLine2.setBackgroundColor(Color.parseColor("#aaaaaa"));

        } else if (tabId.equals(tabHostTag[1])) {
            textView2.setText(Html.fromHtml("<font color=#ffcc66>卖时间</font>"));
            tvLine1.setBackgroundColor(Color.parseColor("#aaaaaa"));
            tvLine2.setBackgroundColor(Color.parseColor("#ffcc66"));

        }
    }
    private View getTabView(String text){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.tab_view,null);
        TextView textView= view.findViewById(R.id.tv_text);
        textView.setText(text);
        viewList.add(view);
        return view;
    }
    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_photo:
                    //调用系统相机
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent,1111);
                    popupWindow.dismiss();
                    break;
                case R.id.btn_select:
                    Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
                    // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
                    intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
                    startActivity(intentToPickPic);
                    popupWindow.dismiss();
                    break;
                case R.id.btn_cancel:
                    popupWindow.dismiss();
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1111 && resultCode==RESULT_OK){
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
            Date date=new Date(System.currentTimeMillis());
            String fileName=simpleDateFormat.format(date);
            saveBmp2Gallery(bitmap,fileName);
        }
    }
    public void saveBmp2Gallery(Bitmap bmp, String picName) {
        //1. 获取内容解析者
        ContentResolver contentResolver =getActivity().getContentResolver();
        String fileName = null;
        //系统相册目录
        String galleryPath= Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                +File.separator+"Camera"+File.separator;


        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;

        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, picName+ ".jpg");

            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            if (null != outStream) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //通知相册更新
        MediaStore.Images.Media.insertImage(contentResolver,
                bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        getActivity().sendBroadcast(intent);
    }

}
