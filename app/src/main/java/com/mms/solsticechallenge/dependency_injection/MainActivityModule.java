package com.mms.solsticechallenge.dependency_injection;

import com.mms.solsticechallenge.adapters.MainActivityRecyclerViewAdapter;
import com.mms.solsticechallenge.networking.UserListDAO;
import com.mms.solsticechallenge.networking.UserListDAOImpl;
import com.mms.solsticechallenge.repositories.UserRepository;
import com.mms.solsticechallenge.repositories.UserRepositoryImpl;
import com.mms.solsticechallenge.views.main_activity.MainActivityViewModel;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public abstract class MainActivityModule {

    @Provides
    static MainActivityRecyclerViewAdapter providesMainActivityRecyclerViewAdapter(){
        return new MainActivityRecyclerViewAdapter();
    }

    @Provides
    static UserListDAO providesUserListDAO(Retrofit retrofit){
        return new UserListDAOImpl(retrofit);
    }

    @Provides
    static UserRepository providesUserRepository(UserListDAOImpl userListDAOImpl){
        return new UserRepositoryImpl(userListDAOImpl);
    }

    @Provides
    static MainActivityViewModel providesMainActivityViewModel(UserRepositoryImpl repository){
        return new MainActivityViewModel(repository);
    }

}
