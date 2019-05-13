package com.mms.solsticechallenge.repositories;

import android.util.Pair;

import com.mms.solsticechallenge.model.User;

import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<Pair<List<User>, Integer>> fetchUserListFromWS();
    void resetUserList();
}
