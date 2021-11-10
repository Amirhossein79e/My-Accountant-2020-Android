package com.amirhosseinemadi.myAccountant.model;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amirhosseinemadi.myAccountant.R;
import com.amirhosseinemadi.myAccountant.common.Application;
import com.amirhosseinemadi.myAccountant.common.PrefManager;
import com.amirhosseinemadi.myAccountant.view.ShowChequeActivity;

import java.util.Locale;

import javax.inject.Inject;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class ChequeAlarm extends BroadcastReceiver {

    private PersianDate persianDate;
    @Inject
    PrefManager prefManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getExtras().getInt("key")==1) {

            Application.component.chequeAlarmInject(this);
            persianDate = new PersianDate(intent.getExtras().getLong("date"));
            PersianDateFormat persianDateFormat = new PersianDateFormat("y/m/d");

            Intent intent1 = new Intent(context, ShowChequeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,1,intent1,PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "private");
            notification.setContentTitle(context.getResources().getString(R.string.app_name));
            if (prefManager.getLang().equals("fa"))
            {
                notification.setContentText(context.getString(R.string.cheque_reminder1) + intent.getExtras().getLong("value") + " " + context.getString(R.string.to_date1) + persianDateFormat.format(persianDate));
            }else
                {
                notification.setContentText(context.getString(R.string.cheque_reminder) + intent.getExtras().getLong("value") + " " + context.getString(R.string.to_date) + persianDateFormat.format(persianDate));
            }
            notification.setPriority(NotificationCompat.PRIORITY_MAX);
            notification.setSmallIcon(R.drawable.cheque);
            notification.setLights(Color.argb(99,0,0,255),500,3000);
            notification.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
            notification.setContentIntent(pendingIntent);


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel("private", "myChannel", NotificationManager.IMPORTANCE_HIGH);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                managerCompat.createNotificationChannel(notificationChannel);
                managerCompat.notify(1, notification.build());
            } else {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification.build());
            }


        }

    }
}
