package com.ltg263.happy_dzh.utils;

import android.app.Activity;
import android.content.Intent;

import com.ltg263.happy_dzh.baseManager.BaseManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 作者： litongge
 * 时间：  2017/2/26 14:56
 * 邮箱；ltg263@126.com
 * 描述：跳转Intent
 */

public class IntentUtil {

    /**
     * 跳入指定的类
     *
     * @param cls
     */
    public static void startActivity(Class<? extends Activity> cls) {
        startActivity(cls, null, null);
    }

    public static void startActivityStr(Class<? extends Activity> cls, String keys, String values){
        if (StringUtil.isNotBlank(keys) && StringUtil.isNotBlank(values)) {
            String[] key = {keys};
            String[] value = {values};
            startActivity(cls, key, value);
        }else{
            LogUtil.w("你传入的参数有为空值————————————");
        }
    }
    /**
     * 跳入指定的类
     * 带参数
     * @param cls
     */
    public static void startActivity(Class<? extends Activity> cls, String[] keys, String[] values) {
        Intent intent = new Intent(BaseManager.app, cls);
        if (keys != null && values != null) {
            if (keys.length != values.length) {
                throw new IllegalArgumentException("keys's length must be equals values's length");
            }
            for (int i = 0; i < keys.length; i++) {
                if ("isAdd".equals(keys[i])) {
                    intent.putExtra(keys[i], false);
                } else {
                    intent.putExtra(keys[i], values[i]);
                }
            }
        }
        startActivity(intent);
    }
    public static void startActivity(Intent intent) {
        Activity currentTopActivity = null;
        try {
            currentTopActivity = getCurrentTopActivity();
        } catch (Exception e) {
        }
        if (currentTopActivity != null) {
            currentTopActivity.startActivity(intent);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseManager.app.startActivity(intent);
        }
    }

    public static Activity getCurrentTopActivity()
            throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
        Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
        activitiesField.setAccessible(true);
        Map<?, ?> activities = (Map<?, ?>) activitiesField.get(activityThread);
        for (Object activityRecord : activities.values()) {
            Class<?> activityRecordClass = activityRecord.getClass();
            Field pausedField = activityRecordClass.getDeclaredField("paused");
            pausedField.setAccessible(true);
            if (!pausedField.getBoolean(activityRecord)) {
                Field activityField = activityRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(activityRecord);
                return activity;
            }
        }
        return null;
    }

}
