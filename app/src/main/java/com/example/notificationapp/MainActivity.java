package com.example.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button myButton, navigation, snackbar;
    private static  final String CHANNEL_ID = "andriod O";
    private int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationChannel();

        navigation = (Button)findViewById(R.id.button2);
        snackbar = (Button)findViewById(R.id.button3);

        snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mySnackBar = Snackbar.make(view, "Now you know what a snackbar looks like", Snackbar.LENGTH_LONG)
                        .setAction("want more?", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                mySnackBar.show();
            }
        });

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openNavPage = new Intent(MainActivity.this, NavActivity.class);
                startActivity(openNavPage);
            }
        });

        myButton =(Button)findViewById(R.id.button1);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationChannel();
                createBasicNotification();
            }
        });
    }

    private void createBasicNotification() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)//mandatory
                .setContentTitle("This ia my Notification")
                .setContentText("This is me trying out a notification on my andriod app")
                .setPriority(Notification.PRIORITY_HIGH)//mandatory
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());


    }

    private void notificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            CHANNEL_ID = "andriod O";

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Andriod O notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Notifaction Channel for andriod O and above");
            NotificationManager notificationManager= getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
