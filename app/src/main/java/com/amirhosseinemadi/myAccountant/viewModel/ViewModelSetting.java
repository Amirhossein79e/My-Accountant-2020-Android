package com.amirhosseinemadi.myAccountant.viewModel;

import androidx.appcompat.app.AppCompatActivity;

public class ViewModelSetting {

    private AppCompatActivity activity;

    public ViewModelSetting(AppCompatActivity activity)
    {
        this.activity = activity;
    }

    public void return1()
    {
        activity.finish();
    }
}
