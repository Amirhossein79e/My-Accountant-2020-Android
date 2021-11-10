package com.amirhosseinemadi.myAccountant.common;


import android.content.Context;


public class Application extends android.app.Application {

    public static Component component;

    @Override
    public void onCreate() {
        component = DaggerComponent.builder().module(new Module(getApplicationContext())).build();
        super.onCreate();
    }

}
