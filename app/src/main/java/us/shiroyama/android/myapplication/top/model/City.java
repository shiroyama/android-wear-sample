package us.shiroyama.android.myapplication.top.model;

import android.content.Context;

import us.shiroyama.android.myapplication.R;

/**
 * Created by shiroyama on 2014/06/14.
 */
public enum City {
    SAPPORO(R.string.sapporo, "Sapporo-shi,JP"),
    AOMORI(R.string.aomori, "Aomori-shi,JP"),
    KANAZAWA(R.string.kanazawa, "Kanazawa-shi,JP"),
    TOKYO(R.string.tokyo, "Tokyo,JP"),
    KYOTO(R.string.kyoto, "Kyoto,JP"),
    HIROSHIMA(R.string.hiroshima, "Hiroshima-shi,JP"),
    FUKUOKA(R.string.fukuoka, "Fukuoka-shi,JP");

    private int mResourceId;
    private String mLocationQuery;

    private City(int resourceId, String locationQuery) {
        mResourceId = resourceId;
        mLocationQuery = locationQuery;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public String getLocationQuery() {
        return mLocationQuery;
    }

    public static City valueOf(Context context, String cityName) {
        for (City city : values()) {
            if (cityName.equals(context.getString(city.getResourceId()))) {
                return city;
            }
        }
        throw new IllegalArgumentException("no such city");
    }
}
