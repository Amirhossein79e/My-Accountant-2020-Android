package com.amirhosseinemadi.myAccountant.common;

import android.content.Context;

import com.amirhosseinemadi.myAccountant.model.DbHandler;

import javax.inject.Inject;

import dagger.Provides;

@dagger.Module
public class Module {

    private final Context context;

    @Inject
    public Module(Context context)
    {
        this.context = context;
    }

    @Provides
    public Context context()
    {
        return context;
    }

    @Provides
    public DbHandler dbHandler()
    {
        return new DbHandler();
    }

    @Provides
    public PrefManager prefManager()
    {
        return new PrefManager();
    }


}
