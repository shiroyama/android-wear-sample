package us.shiroyama.android.myapplication.rest;

import javax.inject.Provider;

import retrofit.RestAdapter;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class RestAdapterProvider implements Provider<RestAdapter> {
    private static final String END_POINT = "http://api.openweathermap.org/";

    @Override
    public RestAdapter get() {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(END_POINT)
                .build();
    }

}
