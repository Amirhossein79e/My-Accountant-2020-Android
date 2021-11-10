package com.amirhosseinemadi.myAccountant.viewModel;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.common.FaNumber;
import com.amirhosseinemadi.myAccountant.common.PrefManager;
import com.amirhosseinemadi.myAccountant.model.ChartHandler;
import com.amirhosseinemadi.myAccountant.model.DbCreate;
import com.amirhosseinemadi.myAccountant.model.DbHandler;
import com.amirhosseinemadi.myAccountant.model.Money;
import com.amirhosseinemadi.myAccountant.view.BalanceActivity;
import com.amirhosseinemadi.myAccountant.view.SelectSpendActivity;
import com.amirhosseinemadi.myAccountant.view.SpendActivity;

import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import ir.hamsaa.persiandatepicker.util.PersianHelper;

public class ViewModelMain extends BaseObservable {

    private DbCreate dbCreate;
    private AppCompatActivity activity;
    @Bindable
    public String income = "";
    @Inject
    DbHandler dbHandler;
    @Inject
    PrefManager prefManager;

    @Bindable
    public String getIncome()
    {
        Money money = dbHandler.select();
        if (money.getIncome()!=null){
        Float f = Float.valueOf(money.getIncome());
        income = String.format("%,.0f",f);

        if (prefManager.getLang().equals("fa"))
        {
            income = FaNumber.faNum(income);
        }

        notifyPropertyChanged(BR._all);
        }

        return income;
    }

    public ViewModelMain(DbCreate dbCreate, AppCompatActivity activity)
    {
        this.dbCreate = dbCreate;
        this.activity = activity;
        Application.component.vmMain(this);
        if (prefManager.getStatus())
        {
            dbHandler.def();
            AlarmManager alarmManager = (AlarmManager) Application.component.context().getSystemService(Context.ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,23);
            calendar.set(Calendar.MINUTE,55);
            Intent intent = new Intent(Application.component.context(),ChartHandler.class);
            intent.putExtra("key",1);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Application.component.context(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            prefManager.setStatus(false);
        }


    }


    public void changeIncome()
    {
        Intent intent = new Intent(Application.component.context(), BalanceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Application.component.context().startActivity(intent);
    }

    public void spend()
    {
        Intent intent = new Intent(Application.component.context(), SpendActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Application.component.context().startActivity(intent);
    }

    public void showList()
    {
        Intent intent = new Intent(Application.component.context(), SelectSpendActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Application.component.context().startActivity(intent);
    }

}
