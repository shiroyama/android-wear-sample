package us.shiroyama.android.myapplication.rest.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import us.shiroyama.android.myapplication.rest.entity.WeatherResponse;

/**
 * Created by shiroyama on 2014/06/14.
 */
public interface WeatherApi {

    @GET("/data/2.5/weather")
    void getWeather(@Query("q") String place, Callback<WeatherResponse> callback);

}
