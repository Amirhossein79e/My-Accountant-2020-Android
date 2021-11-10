package com.amirhosseinemadi.myAccountant.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.amirhosseinemadi.myAccountant.BR;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.common.FaNumber;
import com.amirhosseinemadi.myAccountant.common.PrefManager;

import javax.inject.Inject;

public class ViewModelDetial extends BaseObservable {

    private AppCompatActivity activity;
    private Float value;
    private String detial;
    @Inject
    PrefManager prefManager;

    public ViewModelDetial(AppCompatActivity activity)
    {
        this.activity = activity;
        value = (float) activity.getIntent().getExtras().getLong("value");
        detial = activity.getIntent().getExtras().getString("detial");
        Application.component.vmDetailInject(this);
    }

    @Bindable
    public String getValue()
    {
        String value1 = String.format("%,.0f",value);
        if (prefManager.getLang().equals("fa"))
        {
            value1 = FaNumber.faNum(value1);
        }
        notifyPropertyChanged(BR._all);
        return value1;
    }

    @Bindable
    public String getDetial()
    {
        notifyPropertyChanged(BR._all);
        return detial;
    }


    public void return1()
    {
        activity.finish();
    }

}
