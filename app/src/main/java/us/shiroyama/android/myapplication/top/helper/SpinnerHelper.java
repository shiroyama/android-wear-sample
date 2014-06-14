package us.shiroyama.android.myapplication.top.helper;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import us.shiroyama.android.myapplication.common.helper.AbstractContextHelper;
import us.shiroyama.android.myapplication.top.model.City;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class SpinnerHelper extends AbstractContextHelper {

    public void initialize(Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (City city : City.values()) {
            adapter.add(getContext().getString(city.getResourceId()));
        }
        spinner.setAdapter(adapter);
    }

}
