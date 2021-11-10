package com.amirhosseinemadi.myAccountant.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class PrefManager {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PrefManager()
    {
        sharedPreferences = Application.component.context().getSharedPreferences("defManager", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setStatus(Boolean b)
    {
        editor.putBoolean("key",b);
        editor.commit();
    }

    public Boolean getStatus()
    {
        return sharedPreferences.getBoolean("key",true);
    }

    public void setLang(String language)
    {
        editor.putString("lang",language);
        editor.commit();
    }

    public String getLang()
    {
        return sharedPreferences.getString("lang","fa");
    }

    public void setSpinner(Boolean b)
    {
        editor.putBoolean("spinner",b);
        editor.commit();
    }


}
