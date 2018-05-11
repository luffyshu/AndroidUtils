package com.luffy.androidutils.library;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.UUID;

/**
 * 设备相关工具类
 */
public class DeviceUtils {

    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context){
        String uuid = null;
        SharedPreferences mShare = PreferenceManager.getDefaultSharedPreferences(context);
        if(mShare != null){
            uuid = mShare.getString("uuid", "");
        }
        if(TextUtils.isEmpty(uuid)){
            uuid = UUID.randomUUID().toString();
            if (mShare != null) {
                mShare.edit().putString("uuid", uuid).commit();
            }
        }
        return uuid;
    }

}
