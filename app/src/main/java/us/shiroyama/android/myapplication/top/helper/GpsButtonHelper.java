package us.shiroyama.android.myapplication.top.helper;

import android.widget.Button;

import javax.inject.Inject;

import us.shiroyama.android.myapplication.common.helper.AbstractContextHelper;
import us.shiroyama.android.myapplication.common.helper.Toaster;

/**
 * Created by shiroyama on 2014/06/21.
 */
public class GpsButtonHelper extends AbstractContextHelper {
    @Inject
    private Toaster mToaster;

    public void onClick(Button button) {
        mToaster.toast("not implemented yet");
    }
}
