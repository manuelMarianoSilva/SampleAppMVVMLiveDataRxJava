package com.mms.solsticechallenge.root;

import com.mms.solsticechallenge.dependency_injection.MainActivityModule;
import com.mms.solsticechallenge.dependency_injection.view_model_factories.ViewModelFactoryModule;
import com.mms.solsticechallenge.views.detail_activity.DetailActivity;
import com.mms.solsticechallenge.views.main_activity.MainActivity;
import com.mms.solsticechallenge.dependency_injection.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NetworkModule.class,
        MainActivityModule.class,
        ViewModelFactoryModule.class
})
public interface ChallengeAppComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
