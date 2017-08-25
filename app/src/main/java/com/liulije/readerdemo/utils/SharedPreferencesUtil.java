package com.liulije.readerdemo.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;
import java.util.Set;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/14 10:14
 * @备注：
 */
public class SharedPreferencesUtil {
    private static SharedPreferencesUtil preferencesUtil;
    public Context context;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    public static void init(Context context, String preName, int mode) {
        preferencesUtil = new SharedPreferencesUtil();
        preferencesUtil.context = context;
        preferencesUtil.preferences = preferencesUtil.context.getSharedPreferences(preName, mode);
        preferencesUtil.editor = preferencesUtil.preferences.edit();
    }

    public synchronized static SharedPreferencesUtil getInstance() {
        return preferencesUtil;
    }

    private SharedPreferencesUtil() {
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return this.preferences.getBoolean(key, defaultVal);
    }

    public boolean getBoolean(String key) {
        return this.preferences.getBoolean(key, false);
    }


    public String getString(String key, String defaultVal) {
        return this.preferences.getString(key, defaultVal);
    }

    public String getString(String key) {
        return this.preferences.getString(key, null);
    }

    public int getInt(String key, int defaultVal) {
        return this.preferences.getInt(key, defaultVal);
    }

    public int getInt(String key) {
        return this.preferences.getInt(key, 0);
    }


    public float getFloat(String key, float defaultVal) {
        return this.preferences.getFloat(key, defaultVal);
    }

    public float getFloat(String key) {
        return this.preferences.getFloat(key, 0f);
    }

    public long getLong(String key, long defaultVal) {
        return this.preferences.getLong(key, defaultVal);
    }

    public long getLong(String key) {
        return this.preferences.getLong(key, 0l);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defaultVal) {
        return this.preferences.getStringSet(key, defaultVal);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key) {
        return this.preferences.getStringSet(key, null);
    }

    public Map<String, ?> getAll() {
        return this.preferences.getAll();
    }

    public boolean exists(String key) {
        return preferences.contains(key);
    }


    public SharedPreferencesUtil putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
        return this;
    }

    public void commit() {
        editor.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public SharedPreferencesUtil putStringSet(String key, Set<String> value) {
        editor.putStringSet(key, value);
        editor.commit();
        return this;
    }

    public void putObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectVal);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (preferences.contains(key)) {
            String objectVal = preferences.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public SharedPreferencesUtil remove(String key) {
        editor.remove(key);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil removeAll() {
        editor.clear();
        editor.commit();
        return this;
    }
}
