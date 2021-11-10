package com.amirhosseinemadi.myAccountant.viewModel;

import android.content.ContentValues;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;

import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.model.DbHandler;
import com.amirhosseinemadi.myAccountant.model.Money;
import com.amirhosseinemadi.myAccountant.model.Spend;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import java.util.Date;

import javax.inject.Inject;

public class ViewModelAddSpend extends BaseObservable {

    private AppCompatActivity activity;
    private String key;
    private String value;
    private String detail;
    public void setValue(String value)
    {
        this.value = value;
    }
    public String getValue()
    {
        return value;
    }
    public String getDetail()
    {
        return detail;
    }
    public void setDetail(String detial)
    {
        this.detail = detial;
    }
    @Inject
    DbHandler dbHandler;


    public ViewModelAddSpend(AppCompatActivity activity)
    {
        this.activity = activity;
        key = activity.getIntent().getExtras().getString("key");
        Application.component.vmAddSpend(this);
    }

    public void insert()
    {
        if (value!=null) {
            Long val = Long.valueOf(value);
            if (val != 0) {
                Spend spend;
                Money money;
                ContentValues cv = new ContentValues();
                switch (key) {
                    case "rent":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setRent(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "health":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setHealth(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "food":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setFood(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "buy":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setBuy(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "traffic":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setTraffic(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "clothes":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setClothes(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "phone":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setPhone(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "car":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setCar(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "hobby":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setHobby(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "internet":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setInternet(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "other":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setOther(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;

                    case "pay":
                        spend = new Spend();
                        money = dbHandler.select();
                        spend.setPay(val);
                        money.setIncome(money.getIncome() - val);
                        cv.put("income", money.getIncome());
                        update(spend,cv, detail);
                        break;
                }
            } else {
                Snackbar.make(activity.findViewById(R.id.add_spend), R.string.enter_value, BaseTransientBottomBar.LENGTH_LONG).show();
            }
        }else
        {
            Snackbar.make(activity.findViewById(R.id.add_spend), R.string.enter_value, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }

    private void update(Spend spend,ContentValues cv,String detail)
    {
        Date date = new Date();
        spend.setTime(date.getTime());
        spend.setDetial(detail);
        dbHandler.addSpent(spend);
        dbHandler.addSpentMain(cv);
        activity.finish();
    }

    public void return1()
    {
        activity.finish();
    }

}
