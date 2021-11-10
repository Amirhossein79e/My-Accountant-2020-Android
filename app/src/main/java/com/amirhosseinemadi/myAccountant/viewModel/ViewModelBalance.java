package com.amirhosseinemadi.myAccountant.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.model.DbHandler;
import com.amirhosseinemadi.myAccountant.model.Money;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class ViewModelBalance extends BaseObservable {

    public String value;
    private AppCompatActivity activity;
    @Inject
    DbHandler dbHandler;

    public ViewModelBalance(AppCompatActivity activity)
    {

        this.activity = activity;
        Application.component.vmBalanceInject(this);
    }


    public void onClickAdd()
    {
        if (value!=null) {
            Money money = new Money();
            money.setIncome(Long.valueOf(value));
            dbHandler.addIncome(money);
            activity.finish();
        }
        else
        {
            Snackbar.make(activity.findViewById(R.id.add_income), R.string.enter_value, BaseTransientBottomBar.LENGTH_LONG).show();
        }

    }

    public void onClickMin()
    {
        if (value!=null) {
            Money money = new Money();
            money.setIncome(Long.valueOf(value));
            dbHandler.minIncome(money);
            activity.finish();
        }
        else
        {
            Snackbar.make(activity.findViewById(R.id.min_income), R.string.enter_value, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }


    public void return1()
    {
        activity.finish();
    }

}
