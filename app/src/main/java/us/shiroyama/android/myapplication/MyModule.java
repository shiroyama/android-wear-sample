package us.shiroyama.android.myapplication;

import proton.inject.DefaultModule;
import proton.inject.scope.ContextScoped;
import us.shiroyama.android.myapplication.common.helper.Toaster;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class MyModule extends DefaultModule {

    @Override
    protected void configure() {
        super.configure();
        bind(Toaster.class).in(ContextScoped.class);
    }

}
