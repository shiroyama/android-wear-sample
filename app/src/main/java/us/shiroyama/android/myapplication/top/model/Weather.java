package us.shiroyama.android.myapplication.top.model;

import us.shiroyama.android.myapplication.R;

/**
 * Created by shiroyama on 2014/06/16.
 */
public enum Weather {
    CLEAR_DAY("01d", R.drawable.weather_icon_01d),
    CLEAR_NIGHT("01n", R.drawable.weather_icon_01n),
    FEW_CLOUDS_DAY("02d", R.drawable.weather_icon_02d),
    FEW_CLOUDS_NIGHT("02n", R.drawable.weather_icon_02n),
    SCATTERED_CLOUDS_DAY("03d", R.drawable.weather_icon_03d),
    SCATTERED_CLOUDS_NIGHT("03n", R.drawable.weather_icon_03n),
    BROKEN_CLOUDS_DAY("04d", R.drawable.weather_icon_04d),
    BROKEN_CLOUDS_NIGHT("04n", R.drawable.weather_icon_04n),
    SHOWER_RAIN_DAY("09d", R.drawable.weather_icon_09d),
    SHOWER_RAIN_NIGHT("09n", R.drawable.weather_icon_09n),
    RAIN_DAY("10d", R.drawable.weather_icon_10d),
    RAIN_NIGHT("10n", R.drawable.weather_icon_10n),
    THUNDER_STORM_DAY("11d", R.drawable.weather_icon_11d),
    THUNDER_STORM_NIGHT("11n", R.drawable.weather_icon_11n),
    SNOW_DAY("13d", R.drawable.weather_icon_13d),
    SNOW_NIGHT("13n", R.drawable.weather_icon_13n),
    MIST_DAY("50d", R.drawable.weather_icon_50d),
    MIST_NIGHT("50n", R.drawable.weather_icon_50n);

    private String mIconCode;
    private int mIconResourceId;

    private Weather(String iconCode, int iconResourceId) {
        mIconCode = iconCode;
        mIconResourceId = iconResourceId;
    }

    public String getIconCode() {
        return mIconCode;
    }

    public int getIconResourceId() {
        return mIconResourceId;
    }

    public static Weather fromIconCode(String iconCode) {
        for (Weather weather : values()) {
            if (iconCode.equals(weather.getIconCode())) {
                return weather;
            }
        }
        throw new IllegalArgumentException("no suck icon code");
    }

}
