package com.mitsw.androidplayground.application;

import android.app.Application;

import com.mitsw.androidplayground.utils.DimenUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Hill on 2016/1/22.
 */
public class MitswApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
//        Parse.initialize(this, "PTaMe3d1nYhLijYZTUfBzMmGHKn6K92uDHJzjkeY", "D8XPylw5rCx0wmTI9NqT2CG9MHKDnGNDGZ6DLurb");
//        ParseInstallation.getCurrentInstallation().saveInBackground();


        LeakCanary.install(this);

        DimenUtils.initMetrics(this);
    }


}
