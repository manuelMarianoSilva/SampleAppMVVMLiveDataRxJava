package com.mms.solsticechallenge.root;

import android.app.Application;
import android.content.Context;

public class ChallengeApp extends Application {

    private ChallengeAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerChallengeAppComponent.create();
    }

    public static ChallengeAppComponent getApplicationComponent(Context context){
        return ((ChallengeApp) context.getApplicationContext()).component;
    }
}
