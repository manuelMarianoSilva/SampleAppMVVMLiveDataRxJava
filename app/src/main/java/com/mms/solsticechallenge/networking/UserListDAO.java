package com.mms.solsticechallenge.networking;

import com.mms.solsticechallenge.model.User;

import java.util.List;

import io.reactivex.Observable;

public interface UserListDAO {
    Observable<List<User>> fetchListOfUsersFromWS();
}
