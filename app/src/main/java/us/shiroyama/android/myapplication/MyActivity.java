package us.shiroyama.android.myapplication;

import android.app.Notification;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import proton.inject.activity.ProtonActivity;
import us.shiroyama.android.myapplication.common.helper.Toaster;


public class MyActivity extends ProtonActivity {
    @Inject
    private Toaster mToaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("this is title")
                .setContentText("this is text")
                .setSmallIcon(R.drawable.ic_launcher);

        Notification notification = new WearableNotifications.Builder(builder)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);

        mToaster.toast("OK");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
