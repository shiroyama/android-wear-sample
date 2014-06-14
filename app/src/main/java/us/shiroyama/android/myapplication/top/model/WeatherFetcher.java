package us.shiroyama.android.myapplication.top.model;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import us.shiroyama.android.myapplication.common.error.InitializationException;
import us.shiroyama.android.myapplication.rest.api.WeatherApi;
import us.shiroyama.android.myapplication.rest.entity.WeatherResponse;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class WeatherFetcher {
    private static final String DEFAULT_PLACE = "Tokyo,jp";

    @Inject
    private RestAdapter mAdapter;

    private WeatherApi mApi;
    private WeatherCallback mCallback;

    public void onCreate(WeatherCallback callback) {
        mApi = mAdapter.create(WeatherApi.class);
        mCallback = callback;
    }

    public void fetch(final String place) {
        if (mApi == null) {
            throw new InitializationException("API is not initialized.");
        }
        if (mCallback == null) {
            throw new InitializationException("Callback is not passed.");
        }

        mApi.getWeather(place, new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                mCallback.onSuccess(weatherResponse);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                mCallback.onFailure();
            }
        });
    }

    public void onDestroy() {
        if (mCallback != null) {
            mCallback = null;
        }
    }

    public static interface WeatherCallback {
        void onSuccess(WeatherResponse weatherResponse);

        void onFailure();
    }
}
