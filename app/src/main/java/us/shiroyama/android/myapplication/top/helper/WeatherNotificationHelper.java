package us.shiroyama.android.myapplication.top.helper;

import android.app.Notification;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import us.shiroyama.android.myapplication.common.helper.AbstractContextHelper;
import us.shiroyama.android.myapplication.common.helper.Toaster;
import us.shiroyama.android.myapplication.rest.entity.WeatherResponse;

/**
 * Created by shiroyama on 2014/06/16.
 */
public class WeatherNotificationHelper extends AbstractContextHelper {
    private static final int NOTIFICATION_ID = 1;

    @Inject
    private Toaster mToaster;

    @Inject
    private IconHelper mIconHelper;

    public void notifyWeather(WeatherResponse weatherResponse) {
        List<WeatherResponse.Weather> weatherList = weatherResponse.getWeather();
        // multiple weather could be returned
        WeatherResponse.Weather weather = weatherList.get(0);

        String cityName = weatherResponse.getName();
        String description = weather.getDescription();
        String main = weather.getMain();
        String iconCode = weather.getIcon();

        String contentTitle = String.format("%s 's weather", cityName);
        String contentText = String.format("%s (%s)", main, description);

        // Toast on Android phone
        mToaster.toast(String.format("%s %s", contentTitle, contentText));

        // TODO refactor
        NotificationCompat.Builder builder = getBuilder()
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setLargeIcon(mIconHelper.getLargeIcon(iconCode))
                .setSmallIcon(mIconHelper.getSmallIcon(iconCode));
        Notification notification = new WearableNotifications.Builder(builder)
                .addPages(getPages(weatherResponse))
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    // TODO refactor
    private List<Notification> getPages(WeatherResponse weatherResponse) {
        List<Notification> pages = new ArrayList<Notification>();

        WeatherResponse.Sys sys = weatherResponse.getSys();

        // Sunrise
        NotificationCompat.BigTextStyle sunrize = getTextStyle()
                .bigText(getFormattedDate(sys.getSunrise()))
                .setBigContentTitle("Sunrize")
                .setSummaryText("");
        pages.add(getBuilder().setStyle(sunrize).build());

        // Sunset
        NotificationCompat.BigTextStyle sunset = getTextStyle()
                .bigText(getFormattedDate(sys.getSunset()))
                .setBigContentTitle("Sunset")
                .setSummaryText("");
        pages.add(getBuilder().setStyle(sunset).build());

        return pages;
    }

    private NotificationCompat.Builder getBuilder() {
        return new NotificationCompat.Builder(getContext());
    }

    private NotificationCompat.BigTextStyle getTextStyle() {
        return new NotificationCompat.BigTextStyle();
    }

    private CharSequence getFormattedDate(long unixTime) {
        return DateFormat.format("yyyy/MM/dd, E, kk:mm:ss", unixTime);
    }

}
