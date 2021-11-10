package com.amirhosseinemadi.myAccountant.viewModel;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.model.Cheque;
import com.amirhosseinemadi.myAccountant.model.ChequeAlarm;
import com.amirhosseinemadi.myAccountant.model.DbHandler;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import javax.inject.Inject;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class ViewModelAddCheque extends BaseObservable {

    private AppCompatActivity activity;
    private String value;
    private String name;
    private String account;
    private Long date;
    private Long alarm;
    private Calendar calendar;
    @Inject
    DbHandler dbHandler;

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAccount()
    {
        return account;
    }

    public ViewModelAddCheque(AppCompatActivity activity)
    {
        this.activity = activity;
        Application.component.vmAddCheque(this);
    }



    public void setDate()
    {
        final PersianDatePickerDialog datePickerDialog = new PersianDatePickerDialog(activity);
        datePickerDialog.setPositiveButtonString(activity.getString(R.string.ok))
                .setNegativeButton(activity.getString(R.string.cancel))
                .setTodayButton(activity.getString(R.string.today))
                .setTodayButtonVisible(true)
                .setMinYear(1398)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setShowInBottomSheet(true)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        date = persianCalendar.getTimeInMillis();
                    }

                    @Override
                    public void onDismissed() {

                    }
                })
                .show();
    }

    public void setAlarm()
    {
        final PersianDatePickerDialog datePickerDialog = new PersianDatePickerDialog(activity);
        datePickerDialog.setPositiveButtonString(activity.getString(R.string.ok))
                .setNegativeButton(activity.getString(R.string.cancel))
                .setTodayButton(activity.getString(R.string.today))
                .setTodayButtonVisible(true)
                .setMinYear(1398)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setShowInBottomSheet(true)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        alarm = persianCalendar.getTimeInMillis();
                    }

                    @Override
                    public void onDismissed() {

                    }
                })
                .show();
    }

    public void addCheque()
    {

        if (name!=null && value!=null && account!=null && date!=null && alarm!=null)
        {

            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(alarm);
            calendar.set(Calendar.HOUR_OF_DAY,7);
            calendar.set(Calendar.MINUTE,5);
            Long value1 = Long.parseLong(value);
            Cheque cheque = new Cheque();
            cheque.setName(name);
            cheque.setValue(value1);
            cheque.setAccount(account);
            cheque.setDate(date);
            cheque.setAlarm(alarm);
            dbHandler.addCheque(cheque);

            AlarmManager alarmManager = (AlarmManager) Application.component.context().getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(Application.component.context(), ChequeAlarm.class);
            intent.putExtra("name",name);
            intent.putExtra("value",value1);
            intent.putExtra("date",date);
            intent.putExtra("account",account);
            intent.putExtra("key",1);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(Application.component.context(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

            activity.finish();

        }else
        {
            Snackbar.make(activity.findViewById(R.id.ok_chque), R.string.plz_true, BaseTransientBottomBar.LENGTH_LONG).show();
        }

    }

    public void return1()
    {
        activity.finish();
    }

}
