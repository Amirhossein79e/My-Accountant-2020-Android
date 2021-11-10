package com.amirhosseinemadi.myAccountant.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amirhosseinemadi.myAccountant.model.ChartHandler;
import com.amirhosseinemadi.myAccountant.model.Cheque;
import com.amirhosseinemadi.myAccountant.model.ChequeAlarm;
import com.amirhosseinemadi.myAccountant.model.DbHandler;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class RebootListener extends BroadcastReceiver {

    @Inject
    DbHandler dbHandler;

    @Override
    public void onReceive(Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()))
        {
            Application.component.injectReboot(this);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,23);
            calendar.set(Calendar.MINUTE,55);

            Intent intent1 = new Intent(context, ChartHandler.class);
            intent1.putExtra("key",1);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,intent1,PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

            Date date = new Date();

            List<Cheque> chequeList = dbHandler.getCheque();

            for (Cheque cheque : chequeList)
            {
                if (cheque.getAlarm() >= date.getTime())
                {
                    AlarmManager alarmManager2 = (AlarmManager) Application.component.context().getSystemService(Context.ALARM_SERVICE);

                    Intent intent2 = new Intent(Application.component.context(), ChequeAlarm.class);
                    intent.putExtra("name",cheque.getName());
                    intent.putExtra("value",cheque.getValue());
                    intent.putExtra("date",cheque.getDate());
                    intent.putExtra("account",cheque.getAccount());
                    intent.putExtra("key",1);

                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTimeInMillis(cheque.getAlarm());
                    calendar1.set(Calendar.HOUR_OF_DAY,7);
                    calendar1.set(Calendar.MINUTE,5);

                    PendingIntent pendingIntent2 = PendingIntent.getBroadcast(Application.component.context(),1,intent2,PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager2.setExact(AlarmManager.RTC_WAKEUP,calendar1.getTimeInMillis(),pendingIntent2);
                }
            }

        }

    }
}
