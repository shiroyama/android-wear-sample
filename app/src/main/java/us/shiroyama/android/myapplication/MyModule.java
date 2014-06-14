package us.shiroyama.android.myapplication;

import proton.inject.DefaultModule;
import proton.inject.scope.ContextScoped;
import retrofit.RestAdapter;
import us.shiroyama.android.myapplication.common.helper.Toaster;
import us.shiroyama.android.myapplication.rest.RestAdapterProvider;
import us.shiroyama.android.myapplication.top.model.WeatherFetcher;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class MyModule extends DefaultModule {

    @Override
    protected void configure() {
        super.configure();
        bind(Toaster.class).in(ContextScoped.class);
        bind(RestAdapter.class).toProvider(RestAdapterProvider.class).in(ContextScoped.class);
        bind(WeatherFetcher.class).in(ContextScoped.class);
    }

}
