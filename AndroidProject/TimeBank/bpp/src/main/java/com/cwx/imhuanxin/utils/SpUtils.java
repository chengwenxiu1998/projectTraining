package com.cwx.imhuanxin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.cwx.imhuanxin.IMApplication;

//保存
//获取数据
public class SpUtils {
    public static final String IS_NEW_INVITE = "is_new_invite";//新的邀请标记
    private static SpUtils instance = new SpUtils();
    private static SharedPreferences mSP;
    private SpUtils(){

    }

    //单例
    public static SpUtils getInstance(){
        if(mSP == null){
            mSP = IMApplication.getGlobalApplication().getSharedPreferences("im", Context.MODE_PRIVATE);
        }
     return instance;
    }

    //保存
    public void save(String key,Object value){
        if(value instanceof String){
            mSP.edit().putString(key,(String)value).commit();
        }else if(value instanceof Boolean){
            mSP.edit().putBoolean(key,(Boolean)value).commit();
        }else if(value instanceof Integer){
            mSP.edit().putInt(key,(Integer)value).commit();
        }
    }

    //获取数据
    public String getString(String key,String defValue){
        return mSP.getString(key,defValue);
    }

    //获取boolean类型数据
    public boolean getBoolean(String key,boolean defValue){
        return mSP.getBoolean(key,defValue);
    }
    //获取int类型数据
    public int getBoolean(String key,int defValue){
        return mSP.getInt(key,defValue);
    }

}
