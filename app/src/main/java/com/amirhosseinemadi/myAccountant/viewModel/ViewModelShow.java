package com.amirhosseinemadi.myAccountant.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import com.amirhosseinemadi.myAccountant.BR;
import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.common.FaNumber;
import com.amirhosseinemadi.myAccountant.common.PrefManager;
import com.amirhosseinemadi.myAccountant.model.DbHandler;
import com.amirhosseinemadi.myAccountant.model.Spend;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

import javax.inject.Inject;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class ViewModelShow extends BaseObservable {

    private AppCompatActivity activity;
    private long startDate;
    private long endDate;
    private String key;
    private MutableLiveData<List<Spend>> liveData = new MutableLiveData<>();
    private List<Spend> list;
    private String sum = "0";
    @Inject
    DbHandler dbHandler;
    @Inject
    PrefManager prefManager;


    @Bindable
    public String getSum()
    {
        notifyPropertyChanged(BR._all);
        return sum;
    }

    public ViewModelShow(AppCompatActivity activity)
    {
        this.activity = activity;
        key = activity.getIntent().getExtras().getString("key");
        Application.component.vmShow(this);
    }






    public void start()
    {

        PersianDatePickerDialog datePickerDialog = new PersianDatePickerDialog(activity);

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
                        startDate = persianCalendar.getTimeInMillis();
                    }

                    @Override
                    public void onDismissed() {

                    }
                })
                .show();
    }

    public void end()
    {

        PersianDatePickerDialog datePickerDialog = new PersianDatePickerDialog(activity);

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
                        endDate = persianCalendar.getTimeInMillis();
                    }

                    @Override
                    public void onDismissed() {

                    }
                })
                .show();
    }

    public void ok()
    {
        if (startDate==0&&endDate==0)
        {
            String total = activity.getResources().getString(R.string.total);
            list = dbHandler.selectNotDate(key);
            float f = dbHandler.selectNotDateAll(key);
            String val = total + String.format("%,.0f",f);
            if (prefManager.getLang().equals("fa"))
            {
                sum = FaNumber.faNum(val);
            }else
            {
                sum = val;
            }

            liveData.setValue(list);
            notifyPropertyChanged(BR._all);
        }else
            if (startDate!=endDate&&endDate>startDate)
        {
            String total = activity.getResources().getString(R.string.total);
            list = dbHandler.selectDate(startDate, endDate, key);
            float f1 = dbHandler.selectDateAll(startDate,endDate,key);
            String val = total + String.format("%,.0f",f1);
            if (prefManager.getLang().equals("fa"))
            {
                sum = FaNumber.faNum(val);
            }else
            {
                sum = val;
            }
            liveData.setValue(list);
            notifyPropertyChanged(BR._all);
        }else
            {
                Snackbar.make(activity.findViewById(R.id.ok), R.string.date_true, BaseTransientBottomBar.LENGTH_LONG).show();
            }

    }

    public void return1()
    {
        activity.finish();
    }

    @Bindable
    public MutableLiveData<List<Spend>> getLiveData()
    {
        notifyPropertyChanged(BR._all);
        return liveData;
    }

}
