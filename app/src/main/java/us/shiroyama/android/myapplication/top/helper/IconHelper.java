package us.shiroyama.android.myapplication.top.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import us.shiroyama.android.myapplication.common.helper.AbstractContextHelper;
import us.shiroyama.android.myapplication.top.model.Weather;

/**
 * Created by shiroyama on 2014/06/16.
 */
public class IconHelper extends AbstractContextHelper {

    public int getSmallIcon(String iconCode) {
        Weather weather = Weather.fromIconCode(iconCode);
        return weather.getIconResourceId();
    }

    public Bitmap getLargeIcon(String iconCode) {
        return BitmapFactory.decodeResource(getContext().getResources(), getSmallIcon(iconCode));
    }

}
