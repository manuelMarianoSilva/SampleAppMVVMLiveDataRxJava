package com.mms.solsticechallenge.networking;

import com.mms.solsticechallenge.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class UserListDAOImpl implements UserListDAO {

    private Retrofit retrofit;

    @Inject
    public UserListDAOImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<User>> fetchListOfUsersFromWS(){
        return retrofit
                .create(UserListService.class)
                .fetchUserList();
    }
}
