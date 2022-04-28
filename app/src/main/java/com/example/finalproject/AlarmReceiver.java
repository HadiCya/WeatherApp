package com.example.finalproject;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntenet = new Intent(context, MainActivity.class); //creating intent
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,myIntenet,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"mahdi")
                .setSmallIcon(R.drawable.sun_rise_image) //Icon shown when notification is displayed
                .setContentTitle("Weather Update") //Title of notification
                .setContentText("See Today's weather in "+MainActivity.myTask.address+"!") //Description of notification
                .setAutoCancel(true) //When user presses, notification gets canceled.
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH) //Prioritizes this notification
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());

    }
}
