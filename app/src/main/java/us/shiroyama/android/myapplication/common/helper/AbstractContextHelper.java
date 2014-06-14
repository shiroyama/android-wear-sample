package us.shiroyama.android.myapplication.common.helper;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;

import javax.inject.Inject;

/**
 * Created by shiroyama on 2014/06/14.
 */
public abstract class AbstractContextHelper {
    @Inject
    private Context mContext;

    protected Context getContext() {
        return mContext;
    }

    protected Activity getActivity() {
        return (Activity) mContext;
    }

    protected FragmentActivity getFragmentActivity() {
        return (FragmentActivity) mContext;
    }

    protected ActionBarActivity getActionBarActivity() {
        return (ActionBarActivity) mContext;
    }

}
