package com.mms.solsticechallenge.dependency_injection.view_model_factories;

import com.mms.solsticechallenge.views.main_activity.MainActivityViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindsLoginActivityViewModel(MainActivityViewModel viewModel);
}
