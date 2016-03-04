package com.mitsw.androidplayground.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import com.mitsw.androidplayground.R;
import com.mitsw.androidplayground.services.MitswService;

public class NotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        findViewById(R.id.btn_run).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });

        findViewById(R.id.btn_run_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalNotification();
            }
        });

    }

    private void showNormalNotification() {

        Intent intent1 = new Intent(this, MitswService.class);
        intent1.putExtra(MitswService.FROM, 2);
        PendingIntent mContentPi = PendingIntent.getService(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews contentView = new RemoteViews(NotificationActivity.this.getPackageName(), R.layout.notification_layout_normal);
        contentView.setTextViewText(R.id.title, "Hi, there");
        contentView.setTextViewText(R.id.subtitle, "Do you watch me?");

        Notification notification =  new NotificationCompat.Builder(this)
                .setContent(contentView)
                .setContentIntent(mContentPi)
                .setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(R.mipmap.ic_launcher).build();

        NotificationManagerCompat mNotifyManager = NotificationManagerCompat.from(this);
        mNotifyManager.notify((int) System.currentTimeMillis(), notification);

    }

    private void showNotification() {

        Intent intent1 = new Intent(this, MitswService.class);
        intent1.putExtra(MitswService.FROM, 2);
        PendingIntent contentPi = PendingIntent.getService(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent(this, MitswService.class);
        intent2.putExtra(MitswService.FROM, 4);
        PendingIntent bigContentPi = PendingIntent.getService(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews contentView = new RemoteViews(NotificationActivity.this.getPackageName(), R.layout.notification_layout_normal);
        contentView.setTextViewText(R.id.title, "Come closer");
        contentView.setTextViewText(R.id.subtitle, "Show you a secret.");
//        contentView.setOnClickPendingIntent(R.id.root, mContentPi);

        RemoteViews bigContentView = new RemoteViews(NotificationActivity.this.getPackageName(), R.layout.notification_layout_big);
        bigContentView.setTextViewText(R.id.bigViewTtitle, "See");
        bigContentView.setTextViewText(R.id.bigViewSubtitle, "More details here");
        bigContentView.setViewVisibility(R.id.bigViewTtitle, View.VISIBLE);
        bigContentView.setViewVisibility(R.id.bigViewSubtitle, View.VISIBLE);
        bigContentView.setOnClickPendingIntent(R.id.big_root, bigContentPi);


        // bigView.setOnClickPendingIntent() etc..
//
//        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this);
//        notification = mNotifyBuilder.setContentTitle("some string")
//                .setContentText("Slide down on note to expand")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText("big text"))
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                .build();



        Notification notification =  new NotificationCompat.Builder(this)
                .setContent(contentView)
                .setContentIntent(contentPi)
                .setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(R.mipmap.ic_launcher).build();


        notification.bigContentView = bigContentView;


        //NotificationManagerCompat mNotifyManager = NotificationManagerCompat.from(this);

        final NotificationManager mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyManager.notify((int)System.currentTimeMillis(), notification);


//        Notification notif = new Notification.Builder(this)
//                .setContentTitle("New photo from " )
//                .setContentText("subject")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setPriority(Notification.PRIORITY_MAX)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                .setStyle(new Notification.BigPictureStyle()
//                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)))
//                .build();
//
//        mNotifyManager.notify(0, notif);


//        final String someLongText = "fkdljfdldkfj;ldaksjfkladj;flja;lkjdfljadslfjaddfdsfafjdfad" +
//                "fdl;akjf;lkdf;lkaj;flkjda;lkfjadljflk;adsjfladjflk;dfjlkdjflakdfjdaffjdlfjdjjj" +
//                "adjflkjadlkfjad;lkfjad;sljf;ladkjajlkfjad;lksfjl;akdjf;lkdsajf;lkdjfkadj;flkad" +
//                "jf;lkadjfkldas;lkfja;dljf;lkdasjf;lkadjs;lfjas;ldkfj;lkadsjfl;kadljfl;kasdjf;l" +
//                "jdlskfjklda;fjadslkfj;sdalkfj;ladjf;lajdl;fkajld;kfjlajfl;adjfl;kajdl;fjadl;kfj;";
//
//        final Notification.Builder builder = new Notification.Builder(this);
//        builder.setStyle(new Notification.BigTextStyle(builder)
//                .bigText(someLongText)
//                .setBigContentTitle("Big title")
//                .setSummaryText("Big summary"))
//                .setContentTitle("Title")
//                .setContentText("Summary")
//                .setPriority(Notification.PRIORITY_MAX)
//                .setSmallIcon(R.mipmap.ic_launcher);
//
//        final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        nm.notify(0, builder.build());
    }

    public static class SmartPreview extends Notification.Style{

    }
}
