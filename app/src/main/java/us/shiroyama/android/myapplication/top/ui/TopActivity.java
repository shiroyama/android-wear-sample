package us.shiroyama.android.myapplication.top.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemSelected;
import proton.inject.activity.ProtonActivity;
import us.shiroyama.android.myapplication.R;
import us.shiroyama.android.myapplication.rest.entity.WeatherResponse;
import us.shiroyama.android.myapplication.top.helper.SpinnerHelper;
import us.shiroyama.android.myapplication.top.helper.WeatherNotificationHelper;
import us.shiroyama.android.myapplication.top.model.WeatherFetcher;


public class TopActivity extends ProtonActivity implements WeatherFetcher.WeatherCallback {
    private static final String TAG = TopActivity.class.getSimpleName();

    @InjectView(R.id.spinner)
    Spinner mSpinner;

    @Inject
    private WeatherFetcher mWeatherFetcher;

    @Inject
    private SpinnerHelper mSpinnerHelper;

    @Inject
    private WeatherNotificationHelper mNotificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

        mSpinnerHelper.initialize(mSpinner);
        mWeatherFetcher.onCreate(this);
    }

    @OnItemSelected(R.id.spinner)
    void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String locationQuery = mSpinnerHelper.getSelectedCity(parent, view, position, id);
        mWeatherFetcher.fetch(locationQuery);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public void onSuccess(WeatherResponse weatherResponse) {
        mNotificationHelper.notifyWeather(weatherResponse);
        Log.d(TAG, "weather fetch succeeded.");
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "weather fetch failed.");
    }

}
