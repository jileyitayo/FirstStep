package com.example.jil.Chat;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jil.firststep.MainActivity;
import com.example.jil.firststep.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by JIL on 20/03/16.
 */
public class GcmIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    String TAG = "pavan";

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

// The getMessageType() intent parameter must be the intent you received // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);
        Log.d("pavan", "in gcm intent message " + messageType);
        Log.d("pavan", "in gcm intent message bundle " + extras);

        if (!extras.isEmpty()) { // has effect of unparcelling Bundle /
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " +
                        extras.toString());

// If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                String recieved_message = intent.getStringExtra("text_message");
                sendNotification("message recieved :" + recieved_message);
                Intent sendIntent = new Intent("message_recieved");
                sendIntent.putExtra("message", recieved_message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(sendIntent);
            }
        }

// Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
// This is just one simple example of what you might choose to do with // a GCM message.

    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_account_box_black_24dp).setContentTitle("GCM Notification")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(msg)).setContentText(msg);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}