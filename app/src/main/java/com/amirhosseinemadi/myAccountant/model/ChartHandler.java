package com.amirhosseinemadi.myAccountant.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.amirhosseinemadi.myAccountant.common.Application;

import java.util.List;

import javax.inject.Inject;

import saman.zamani.persiandate.PersianDate;

public class ChartHandler extends BroadcastReceiver {

    private PersianDate persianDate;
    private Money money;
    @Inject
    DbHandler dbHandler;

    @Override
    public void onReceive(Context context, Intent intent) {

          if (intent.getExtras().getInt("key") == 1)
        {
            Application.component.chartInject(this);
            persianDate = new PersianDate();
            money = dbHandler.select();
            ChartModel chartModel = new ChartModel();
            chartModel.setTime(persianDate.getTime());
            chartModel.setIncome(money.getIncome());

            List<ChartModel> list = dbHandler.incomeList();
            if (list.size() < 31) {
                 dbHandler.insertChart(chartModel);
            } else
                {
                 dbHandler.deleteChart();
                 dbHandler.insertChart(chartModel);
            }
        }

    }

}




