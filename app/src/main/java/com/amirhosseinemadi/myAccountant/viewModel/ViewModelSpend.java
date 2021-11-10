package com.amirhosseinemadi.myAccountant.viewModel;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.view.AddSpendActivity;

public class ViewModelSpend {

    private AppCompatActivity activity;

    public ViewModelSpend(AppCompatActivity activity)
    {
       this.activity = activity;
    }


    public void rentClick()
    {
        myIntent("rent");
    }

    public void healthClick()
    {
        myIntent("health");
    }

    public void foodClick()
    {
        myIntent("food");
    }

    public void buyClick()
    {
        myIntent("buy");
    }

    public void trafficClick()
    {
        myIntent("traffic");
    }

    public void clothesClick()
    {
        myIntent("clothes");
    }

    public void phoneClick()
    {
        myIntent("phone");
    }

    public void carClick()
    {
        myIntent("car");
    }

    public void hobbyClick()
    {
        myIntent("hobby");
    }

    public void internetClick()
    {
        myIntent("internet");
    }

    public void otherClick()
    {
        myIntent("other");
    }

    public void payClick()
    {
        myIntent("pay");
    }


    private void myIntent(String key)
    {
        Intent intent = new Intent(Application.component.context(), AddSpendActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("key",key);
        Application.component.context().startActivity(intent);
    }

    public void return1()
    {
        activity.finish();
    }
}
