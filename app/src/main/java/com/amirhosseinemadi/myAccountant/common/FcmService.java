package com.amirhosseinemadi.myAccountant.common;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amirhosseinemadi.myAccountant.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s)
    {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(Application.component.context(),"accountant");
        notification.setContentTitle(remoteMessage.getNotification().getTitle());
        notification.setContentText(remoteMessage.getNotification().getBody());
        notification.setPriority(NotificationManager.IMPORTANCE_DEFAULT);
        notification.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Application.component.context());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("accountant","accountant",NotificationManager.IMPORTANCE_DEFAULT);
            managerCompat.createNotificationChannel(channel);
        }
        managerCompat.notify(1,notification.build());


    }
}
