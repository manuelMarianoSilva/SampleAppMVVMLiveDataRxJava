package com.mms.solsticechallenge.views.main_activity;

import android.util.Log;
import android.util.Pair;

import com.mms.solsticechallenge.model.User;
import com.mms.solsticechallenge.repositories.UserRepository;
import com.mms.solsticechallenge.repositories.UserRepositoryImpl;
import com.mms.solsticechallenge.utils.UserListContainer;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    public static final String TAG = "UserRepoTag";

    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isInternetOnline = new MutableLiveData<>();
    MutableLiveData<Boolean> errorLoading = new MutableLiveData<>();
    private UserRepository repository;

    @Inject
    public MainActivityViewModel(UserRepository repository) {
        this.repository = repository;
        setViewValues();
    }

    public void fetchUserList() {
        repository.fetchUserListFromWS()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleResults(Pair<List<User>, Integer> sortedUsers){
        UserListContainer.referenceUserList.setValue(sortedUsers.first);
        UserListContainer.referenceNumberOfFavorites.setValue(sortedUsers.second);
    }

    private void handleError (Throwable e){
        errorLoading.setValue(true);
        Log.e(TAG, "handleError: ", e );
    }


    private void setViewValues() {
        isInternetOnline.setValue(true);
        isLoading.setValue(false);
        errorLoading.setValue(false);
    }

    public void resetUserList() {
        repository.resetUserList();
    }
}
