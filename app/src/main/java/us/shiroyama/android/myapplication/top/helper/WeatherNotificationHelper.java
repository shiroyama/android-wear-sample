package us.shiroyama.android.myapplication.top.helper;

import android.app.Notification;
import android.graphics.BitmapFactory;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;

import java.util.List;

import javax.inject.Inject;

import us.shiroyama.android.myapplication.R;
import us.shiroyama.android.myapplication.common.helper.AbstractContextHelper;
import us.shiroyama.android.myapplication.common.helper.Toaster;
import us.shiroyama.android.myapplication.rest.entity.WeatherResponse;

/**
 * Created by shiroyama on 2014/06/16.
 */
public class WeatherNotificationHelper extends AbstractContextHelper {

    @Inject
    private Toaster mToaster;

    public void notifyWeather(WeatherResponse weatherResponse) {
        List<WeatherResponse.Weather> weatherList = weatherResponse.getWeather();
        String cityName = weatherResponse.getName();
        // multiple weather could be returned
        WeatherResponse.Weather weather = weatherList.get(0);
        String icon = weather.getIcon();
        String description = weather.getDescription();
        String main = weather.getMain();

        String title = String.format("%s 's weather: %s", cityName, main);
        mToaster.toast(title);

        // TODO refactor
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                .setContentTitle(cityName)
                .setContentText(description)
                        // experimental: large icon is shown on the back of the notification window
                .setLargeIcon(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_launcher))
                        // experimental: small icon is shown on the center of the notification window
                .setSmallIcon(R.drawable.ic_launcher);
        Notification notification = new WearableNotifications.Builder(builder)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.notify(1, notification);
    }

}
