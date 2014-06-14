package us.shiroyama.android.myapplication.top.ui;

import android.app.Notification;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemSelected;
import proton.inject.activity.ProtonActivity;
import us.shiroyama.android.myapplication.R;
import us.shiroyama.android.myapplication.common.helper.Toaster;
import us.shiroyama.android.myapplication.rest.entity.WeatherResponse;
import us.shiroyama.android.myapplication.top.helper.SpinnerHelper;
import us.shiroyama.android.myapplication.top.model.City;
import us.shiroyama.android.myapplication.top.model.WeatherFetcher;


public class MyActivity extends ProtonActivity {
    private static final String TAG = MyActivity.class.getSimpleName();

    @InjectView(R.id.spinner)
    Spinner mSpinner;

    @Inject
    private Toaster mToaster;

    @Inject
    private WeatherFetcher mWeatherFetcher;

    @Inject
    private SpinnerHelper mSpinnerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

        mSpinnerHelper.initialize(mSpinner);
        mWeatherFetcher.onCreate(new WeatherFetcher.WeatherCallback() {
            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                notifyWeather(weatherResponse);
                Log.e(TAG, "weather fetch succeeded.");
            }

            @Override
            public void onFailure() {
                Log.e(TAG, "weather fetch failed.");
            }
        });
    }

    private void notifyWeather(WeatherResponse weatherResponse) {
        List<WeatherResponse.Weather> weatherList = weatherResponse.getWeather();
        String cityName = weatherResponse.getName();
        WeatherResponse.Weather weather = weatherList.get(0);
        String icon = weather.getIcon();
        String description = weather.getDescription();
        String main = weather.getMain();

        String title = String.format("%s 's weather: %s", cityName, main);
        mToaster.toast(title);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                //.setContentTitle(title)
                .setContentTitle(cityName)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_launcher);
        Notification notification = new WearableNotifications.Builder(builder)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);
    }

    @OnItemSelected(R.id.spinner)
    void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        String item = (String) spinner.getSelectedItem();
        City chosenCity = City.valueOf(this, item);
        String locationQuery = chosenCity.getLocationQuery();
        mWeatherFetcher.fetch(locationQuery);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWeatherFetcher != null) {
            mWeatherFetcher.onDestroy();
        }
    }
}
