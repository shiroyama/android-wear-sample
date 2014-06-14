package us.shiroyama.android.myapplication.top.ui;

import android.app.Notification;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import proton.inject.activity.ProtonActivity;
import us.shiroyama.android.myapplication.R;
import us.shiroyama.android.myapplication.common.helper.Toaster;
import us.shiroyama.android.myapplication.rest.entity.WeatherResponse;
import us.shiroyama.android.myapplication.top.model.WeatherFetcher;


public class MyActivity extends ProtonActivity {
    @Inject
    private Toaster mToaster;

    @Inject
    private WeatherFetcher mWeatherFetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mWeatherFetcher.onCreate(new WeatherFetcher.WeatherCallback() {
            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                notifyWeather(weatherResponse);
                mToaster.toast("OK");
            }

            @Override
            public void onFailure() {
                mToaster.toast("NG");
            }
        });
        mWeatherFetcher.fetch();

    }

    private void notifyWeather(WeatherResponse weatherResponse) {
        List<WeatherResponse.Weather> weatherList = weatherResponse.getWeather();
        WeatherResponse.Weather weather = weatherList.get(0);
        String icon = weather.getIcon();
        String description = weather.getDescription();
        String main = weather.getMain();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(main)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_launcher);
        Notification notification = new WearableNotifications.Builder(builder)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);
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
