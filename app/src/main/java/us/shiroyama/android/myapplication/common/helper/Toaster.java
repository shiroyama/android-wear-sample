package us.shiroyama.android.myapplication.common.helper;

import android.widget.Toast;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class Toaster extends AbstractContextHelper {

    public void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
