package com.jithin.core.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static com.jithin.core.util.Constants.USER_PREF;


public class SharedPref {
    private static SharedPref instance = null;
    private Context context;
    public SharedPreferences settings;
    public SharedPreferences.Editor editor;

    private SharedPref() {
    }

    public static SharedPref getInstance() {
        if (instance == null) {
            instance = new SharedPref();
        }
        return instance;
    }

    public static SharedPref getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPref(context);
        }
        return instance;
    }

    public boolean isInitialized() {
        return settings != null;
    }

    private SharedPref(Context context) {
        this.context = context;
        settings = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public void init(Context context) {
        this.context = context.getApplicationContext();
        settings = this.context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

    }

    public Object getPref(String variName) {

        if (settings != null) {
            return settings.getString(variName, null);
        } else
            return null;

    }


    public Object getPref(String variName, String defaultVal) {
        if (settings != null) {
            return settings.getString(variName, defaultVal);
        } else
            return defaultVal;
    }

    public boolean getPrefBool(String variname) {
        if (settings == null) return false;
        return settings.getBoolean(variname, false);
    }

    public String getPrefString(String variName) {
        if (settings == null) return null;

        return settings.getString(variName, null);
    }

    public Serializable getPrefSerializable(String variName) {
        if (settings == null) return null;
        return stringToObject(settings.getString(variName, null));
    }

    public int getPrefInt(String variName) {
        if (settings == null) return 0;
        return settings.getInt(variName, 0);
    }

    public void setPref(String variName, Object value) {
        if (settings != null && value != null) {
            editor = settings.edit();
            editor.putString(variName, value.toString());
            editor.apply();
        }
    }

    public void setPref(String variName, boolean value) {
        if (settings != null) {
            editor = settings.edit();
            editor.putBoolean(variName, value);
            editor.apply();
        }
    }

    public void setPrefSerializable(String variName, Serializable value) {
        if (settings != null && value != null) {
            editor = settings.edit();
            editor.putString(variName, objectToString(value));
            editor.apply();
        }
    }

    public void setPrefInt(String variName, int value) {
        if (settings != null) {
            editor = settings.edit();
            editor.putInt(variName, value);
            editor.apply();
        }
    }

    public void setPrefString(String key, String value) {
        if (settings != null) {
            editor = settings.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }





    /*public void setPref(String variName, int value) {
        if (settings != null) {
			editor = settings.edit();
			editor.putInt(variName, value);
			editor.apply();
		}
	}*/

    public void removePref(String variName) {
        if (settings != null) {
            editor = settings.edit();
            editor.remove(variName);
            editor.apply();
        }
    }

    static public String objectToString(Serializable object) {
        String encoded = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            encoded = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoded;
    }

    @SuppressWarnings("unchecked")
    static public Serializable stringToObject(String string) {
        byte[] bytes = Base64.decode(string, 0);
        Serializable object = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            object = (Serializable) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return object;
    }
}
